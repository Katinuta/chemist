package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.PrescripDetailDAO;
import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;

/**
 * The Class PrescripDetailService.
 */
public class PrescripDetailService {
    /**
     * The prescription DAO.
     */
    private PrescriptionDAO prescriptionDAO;

    /**
     * The prescrip detail DAO.
     */
    private PrescripDetailDAO prescripDetailDAO;

    /**
     * Instantiates a new prescrip detail service.
     */
    public PrescripDetailService() {
        prescripDetailDAO = DAOFactory.getDAOFactory().getPrescripDetailDAO();
        prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
    }

    /**
     * Update prescrip detail.
     *
     * @param detail the detail
     * @return the prescription detail
     * @throws ServiceException the service exception
     */
    public PrescriptionDetail updatePrescripDetail(PrescriptionDetail detail) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(prescripDetailDAO);

        try {
            PrescriptionDetail oldDetail = prescripDetailDAO.findById(detail.getDetailId());
            if (oldDetail == null) {
                throw new ServiceException("Prescription detail is not found");
            }
            manager.beginTransaction(prescriptionDAO);
            Prescription oldPrescription = prescriptionDAO.findById(oldDetail.getPrescription().getPrescriptionId());
            if (oldPrescription == null) {
                throw new ServiceException("Prescription to extend  is not found");
            }
            oldDetail.setStatus(detail.getStatus());
            oldPrescription.setStatus(detail.getStatus());
            manager.beginTransaction(prescripDetailDAO, prescriptionDAO);
            prescripDetailDAO.update(oldDetail);
            prescriptionDAO.update(oldPrescription);
            manager.commit();
        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException("Request for extending  prescription detail is not sent", e);
        } finally {
            manager.endTransaction();
        }
        return detail;
    }

}
