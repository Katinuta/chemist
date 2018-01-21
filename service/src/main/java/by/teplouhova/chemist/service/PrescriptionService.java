package by.teplouhova.chemist.service;

import by.teplouhova.chemist.dao.PrescripDetailDAO;
import by.teplouhova.chemist.dao.PrescriptionDAO;
import by.teplouhova.chemist.dao.TransactionManager;
import by.teplouhova.chemist.dao.exception.DAOException;
import by.teplouhova.chemist.dao.factory.DAOFactory;
import by.teplouhova.chemist.entity.impl.Medicine;
import by.teplouhova.chemist.entity.impl.Prescription;
import by.teplouhova.chemist.entity.impl.PrescriptionDetail;
import by.teplouhova.chemist.entity.impl.PrescriptionStatus;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PrescriptionService {
    private static final Logger LOGGER = LogManager.getLogger();

    public Prescription getPriscription(long id) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        PrescriptionDAO prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
        PrescripDetailDAO prescripDetailDAO = DAOFactory.getDAOFactory().getPrescripDetailDAO();
        manager.beginTransaction(prescriptionDAO);

        Prescription prescription;
        try {
            prescription = prescriptionDAO.findById(id);

            if (prescription != null) {
                manager.beginTransaction(prescripDetailDAO);
                List<PrescriptionDetail> details = prescripDetailDAO.findAllByPrescriptionId(id);
                if (details != null) {
                    details.stream().forEach(detail -> prescription.setDetails(detail));

                }

            }


        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return prescription;
    }

    public void updatePriscription(Prescription prescription) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        PrescriptionDAO prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
        PrescripDetailDAO prescripDetailDAO = DAOFactory.getDAOFactory().getPrescripDetailDAO();
        manager.beginTransaction(prescriptionDAO, prescripDetailDAO);
        try {
            prescriptionDAO.update(prescription);
            Iterator<PrescriptionDetail> iterator = prescription.getDetailsIterator();
            while (iterator.hasNext()) {
                PrescriptionDetail detail = iterator.next();
                prescripDetailDAO.update(detail);
            }
            manager.commit();

        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }

    }

    public List<Prescription> getDoctorPrescriptions(long doctorId) throws ServiceException {
        List<Prescription> prescriptions = null;
        TransactionManager manager = new TransactionManager();
        PrescriptionDAO prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
        manager.beginTransaction(prescriptionDAO);
        try {
            prescriptions = prescriptionDAO.findPrescriptionByDoctorId(doctorId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return prescriptions;
    }

    public boolean checkPrescripExistForMedicine(Medicine medicine, int count, long clientId) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        PrescriptionDAO prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
        PrescripDetailDAO prescripDetailDAO=DAOFactory.getDAOFactory().getPrescripDetailDAO();
        manager.beginTransaction(prescriptionDAO);
        try {
            List<Prescription> prescriptions = prescriptionDAO.findPrescriptionByClientId(clientId);
            prescriptions=prescriptions
                    .stream()
                    .filter(prescription -> prescription.getStatus()== PrescriptionStatus.ACTIVE)
                    .collect(Collectors.toList());

            if (!prescriptions.isEmpty()){
                manager.beginTransaction(prescripDetailDAO);
                for (Prescription prescription : prescriptions) {
                    PrescriptionDetail detail=
                            prescripDetailDAO
                                    .findByPrescripIdMedicineId(prescription.getPrescriptionId(), medicine.getMedicineId());
                    if(detail!=null){
                        if(detail.getQuantityPack()>=count){
                            return true;
                        }
                    }
                }
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }finally {
            manager.endTransaction();
        }
        return false;
    }
}
