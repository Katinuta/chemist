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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PrescriptionService {
    private static final Logger LOGGER = LogManager.getLogger();

    private PrescriptionDAO prescriptionDAO;
    private PrescripDetailDAO prescripDetailDAO;

    public PrescriptionService() {
        prescriptionDAO = DAOFactory.getDAOFactory().getPrescriptionDAO();
        prescripDetailDAO = DAOFactory.getDAOFactory().getPrescripDetailDAO();
    }

    public Prescription getPrescription(long prescriptionId) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(prescriptionDAO);
        Prescription prescription;
        try {
            prescription = prescriptionDAO.findById(prescriptionId);
            if (prescription == null) {
                throw new ServiceException("Prescription is not found ID: " + prescriptionId);
            }
            manager.beginTransaction(prescripDetailDAO);
            List<PrescriptionDetail> details = prescripDetailDAO.findAllByPrescriptionId(prescriptionId);
            if (details == null) {
                throw new ServiceException("Details prescription is not found ID: " + prescriptionId);
            }
            details.stream().forEach(detail -> prescription.setDetails(detail));
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return prescription;
    }

    public void update(Prescription newPrescrip) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        try {
            Prescription oldPrescrip = getPrescription(newPrescrip.getPrescriptionId());
            manager.beginTransaction(prescriptionDAO, prescripDetailDAO);
            if (oldPrescrip == null) {
                throw new ServiceException("Prescription is not found id:" + newPrescrip.getPrescriptionId());
            }
            oldPrescrip.setStatus(newPrescrip.getStatus());
            List<PrescriptionDetail> details = oldPrescrip.getDetails();
            if(PrescriptionStatus.EXTEND.equals(oldPrescrip.getStatus())){
                details.stream()
                        .filter(detail -> PrescriptionStatus.INACTIVE.equals( detail.getStatus()))
                        .forEach(detail -> detail.setStatus(PrescriptionStatus.EXTEND));

            }else{
                oldPrescrip.setDateEnd(newPrescrip.getDateEnd());
                details.stream()
                        .filter(detail -> PrescriptionStatus.EXTEND.equals(detail.getStatus()))
                        .forEach(detail -> detail.setStatus(PrescriptionStatus.ACTIVE));

            }
            prescriptionDAO.update(oldPrescrip);
            Iterator<PrescriptionDetail> iterator = oldPrescrip.getDetailsIterator();
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

    public List<Prescription> getDoctorPrescriptions(long doctorId, boolean isExtend) throws ServiceException {
        List<Prescription> prescriptions;
        TransactionManager manager = new TransactionManager();
        try {
            if (isExtend) {
                prescriptions = prescriptionDAO.findPrescripByDoctorIdExtand(doctorId);
            } else {
                prescriptions = prescriptionDAO.findPrescriptionByDoctorId(doctorId);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return prescriptions;
    }


    public boolean checkPrescripExistForMedicine(Medicine medicine, int count, long clientId) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(prescriptionDAO);
        try {
            List<Prescription> prescriptions = prescriptionDAO.findPrescriptionByClientId(clientId);
            prescriptions = prescriptions
                    .stream()
                    .filter(prescription -> prescription.getStatus() == PrescriptionStatus.ACTIVE)
                    .collect(Collectors.toList());

            if (!prescriptions.isEmpty()) {
                manager.beginTransaction(prescripDetailDAO);
                for (Prescription prescription : prescriptions) {
                    PrescriptionDetail detail =
                            prescripDetailDAO
                                    .findByPrescripIdMedicineId(prescription.getPrescriptionId(), medicine.getMedicineId());
                    if (detail != null) {
                        if (detail.getQuantityPack() >= count) {
                            return true;
                        }
                    }
                }
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            manager.endTransaction();
        }
        return false;
    }

    public void createPrescription(Prescription prescription) throws ServiceException {
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(prescriptionDAO, prescripDetailDAO);
        try {
            List<PrescriptionDetail> details = prescription.getDetails();
            prescriptionDAO.create(prescription);
            details.stream().forEach(detail -> detail.setPrescription(prescription));
            for (PrescriptionDetail detail : details) {
                prescripDetailDAO.create(detail);
            }
            manager.commit();
        } catch (DAOException e) {
            manager.rollback();
            throw new ServiceException("Exception in method createPrescription", e);
        } finally {
            manager.endTransaction();
        }

    }
}
