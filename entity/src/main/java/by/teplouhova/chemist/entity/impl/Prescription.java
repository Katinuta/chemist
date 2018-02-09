package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class Prescription.
 */
public class Prescription extends Entity {

    /** The prescription id. */
    private long prescriptionId;

    /** The date begin. */
    private LocalDate dateBegin;

    /** The date end. */
    private LocalDate dateEnd;

    /** The client. */
    private User client;

    /** The doctor. */
    private User doctor;

    /** The status. */
    private PrescriptionStatus status;

    /** The details. */
    private List<PrescriptionDetail> details;

    /**
     * Instantiates a new prescription.
     */
    public Prescription() {
        details=new ArrayList<>();
        status=PrescriptionStatus.ACTIVE;
        client=new User();
        doctor=new User();
    }

    /**
     * Instantiates a new prescription.
     *
     * @param prescriptionId the prescription id
     */
    public Prescription(long prescriptionId) {
        this.prescriptionId = prescriptionId;
        details=new ArrayList<>();
        status=PrescriptionStatus.ACTIVE;
    }

    /**
     * Gets the prescription id.
     *
     * @return the prescription id
     */
    public long getPrescriptionId() {
        return prescriptionId;
    }

    /**
     * Sets the prescription id.
     *
     * @param prescriptionId the new prescription id
     */
    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    /**
     * Gets the date begin.
     *
     * @return the date begin
     */
    public LocalDate getDateBegin() {
        return dateBegin;
    }

    /**
     * Sets the date begin.
     *
     * @param dateBegin the new date begin
     */
    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    /**
     * Gets the date end.
     *
     * @return the date end
     */
    public LocalDate getDateEnd() {
        return dateEnd;
    }

    /**
     * Sets the date end.
     *
     * @param dateEnd the new date end
     */
    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Gets the client.
     *
     * @return the client
     */
    public User getClient() {
        return client;
    }

    /**
     * Sets the client.
     *
     * @param client the new client
     */
    public void setClient(User client) {
        this.client = client;
    }

    /**
     * Gets the doctor.
     *
     * @return the doctor
     */
    public User getDoctor() {
        return doctor;
    }

    /**
     * Sets the doctor.
     *
     * @param doctor the new doctor
     */
    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public PrescriptionStatus getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    /**
     * Sets the details.
     *
     * @param detail the new details
     */
    public void setDetails(PrescriptionDetail detail){
        details.add(detail);
    }

    /**
     * Gets the details iterator.
     *
     * @return the details iterator
     */
    public Iterator<PrescriptionDetail> getDetailsIterator() {
        return details.iterator();
    }

    /**
     * Checks if is empty details.
     *
     * @return true, if is empty details
     */
    public boolean isEmptyDetails(){
        return details.isEmpty();
    }

    /**
     * Gets the details.
     *
     * @return the details
     */
    public List<PrescriptionDetail> getDetails() {
        return details;
    }

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prescription that = (Prescription) o;

        if (prescriptionId != that.prescriptionId) return false;
        if (dateBegin != null ? !dateBegin.equals(that.dateBegin) : that.dateBegin != null) return false;
        if (dateEnd != null ? !dateEnd.equals(that.dateEnd) : that.dateEnd != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (doctor != null ? !doctor.equals(that.doctor) : that.doctor != null) return false;
        if (status != that.status) return false;
        return details != null ? details.equals(that.details) : that.details == null;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int result = (int) (prescriptionId ^ (prescriptionId >>> 32));
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", client=" + client +
                ", doctor=" + doctor +
                ", status=" + status +
                ", details=" + details +
                '}';
    }
}
