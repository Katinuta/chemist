package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.*;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Class OrderService.
 */
public class OrderService {

    /**
     * The order DAO.
     */
    private OrderDAO orderDAO;

    /**
     * The order detail DAO.
     */
    private OrderDetailDAO orderDetailDAO;

    /**
     * The user DAO.
     */
    private UserDAO userDAO;

    /**
     * The medicine DAO.
     */
    private MedicineDAO medicineDAO;

    /**
     * The prescrip detail DAO.
     */
    private PrescripDetailDAO prescripDetailDAO;

    /**
     * The prescription DAO.
     */
    private PrescriptionDAO prescriptionDAO;


    /**
     * Instantiates a new order service.
     */
    public OrderService() {
        orderDAO = DAOFactory.getDAOFactory().getOrderDAO();
        orderDetailDAO = DAOFactory.getDAOFactory().getOrderDetailDAO();
        userDAO = DAOFactory.getDAOFactory().getUserDAO();
        medicineDAO = DAOFactory.getDAOFactory().getMedicineDAO();
        prescripDetailDAO = DAOFactory.getDAOFactory().getPrescripDetailDAO();
        prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
    }

    /**
     * Gets the by id.
     *
     * @param orderId the order id
     * @return the by id
     * @throws ServiceException the service exception
     */
    public Order getById(long orderId) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDAO);
        Order order;
        try {
            order = orderDAO.findById(orderId);
            if (order == null) {
                throw new ServiceException("Order by id is not found id:" + orderId);
            }
            manager.beginTransaction(orderDetailDAO);
            List<OrderDetail> details = orderDetailDAO.findAllByOrderId(order.getOrderId());
            order.setDetails(details);
        } catch (DAOException e) {
            throw new ServiceException("Order by id is not found id:" + orderId);
        } finally {
            manager.endTransaction();
        }
        return order;
    }

    /**
     * Creates the.
     *
     * @param order the order
     * @throws ServiceException the service exception
     */
    public void create(Order order) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDAO, orderDetailDAO, userDAO, medicineDAO, prescripDetailDAO);
        try {
            User user = userDAO.findById(order.getUser().getUserId());
            BigDecimal balance = user.getAccount();
            BigDecimal newBalance = balance.subtract(order.getTotal());
            user.setAccount(newBalance);
            userDAO.update(user);
            order.setStatus(OrderStatusType.PAID);
            List<OrderDetail> details = order.getDetails();
            orderDAO.create(order);
            order = orderDAO.findById(order.getOrderId());
            for (OrderDetail detail : details) {
                detail.setOrder(order);
                Medicine medicine = detail.getMedicine();

                if (medicine.getIsNeedRecipe()) {
                    PrescriptionDetail prescripDetail = prescripDetailDAO.findByUserIdMedicineId(user.getUserId(), medicine.getMedicineId());
                    if (prescripDetail == null) {
                        throw new ServiceException("Prescription for medicine in cart is not found");
                    }
                    prescripDetail.setStatus(PrescriptionStatus.USED);
                    Prescription prescription = prescripDetail.getPrescription();
                    prescription.setStatus(PrescriptionStatus.USED);
                    prescripDetailDAO.update(prescripDetail);
                    prescriptionDAO.update(prescription);
                }

                medicine = medicineDAO.findByIdEdit(medicine.getMedicineId());
                medicine.setQuantityPackages(medicine.getQuantityPackages() - detail.getQuantity());
                medicineDAO.update(medicine);
                orderDetailDAO.create(detail);
            }
            order.setDetails(details);
            manager.commit();
        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException("Order is not issued", e);
        } finally {
            manager.endTransaction();
        }

    }
}
