package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

/**
 * The Class PrescriptionDetail.
 */
public class PrescriptionDetail extends Entity {

    /** The detail id. */
    private long detailId;

    /** The quantity pack. */
    private int quantityPack;

    /** The prescription. */
    private Prescription prescription;

    /** The medicine. */
    private Medicine medicine;

    /** The status. */
    private PrescriptionStatus status;

    /**
     * Instantiates a new prescription detail.
     */
    public PrescriptionDetail() {
        medicine = new Medicine();
        status = PrescriptionStatus.ACTIVE;
        prescription = new Prescription();
    }


    /**
     * Gets the detail id.
     *
     * @return the detail id
     */
    public long getDetailId() {
        return detailId;
    }

    /**
     * Sets the detail id.
     *
     * @param detailId the new detail id
     */
    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    /**
     * Gets the quantity pack.
     *
     * @return the quantity pack
     */
    public int getQuantityPack() {
        return quantityPack;
    }

    /**
     * Sets the quantity pack.
     *
     * @param quantityPack the new quantity pack
     */
    public void setQuantityPack(int quantityPack) {
        this.quantityPack = quantityPack;
    }

    /**
     * Gets the prescription.
     *
     * @return the prescription
     */
    public Prescription getPrescription() {
        return prescription;
    }

    /**
     * Sets the prescription.
     *
     * @param prescription the new prescription
     */
    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    /**
     * Gets the medicine.
     *
     * @return the medicine
     */
    public Medicine getMedicine() {
        return medicine;
    }

    /**
     * Sets the medicine.
     *
     * @param medicine the new medicine
     */
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
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
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrescriptionDetail that = (PrescriptionDetail) o;

        if (detailId != that.detailId) return false;
        if (quantityPack != that.quantityPack) return false;
        if (prescription != null ? !prescription.equals(that.prescription) : that.prescription != null) return false;
        if (medicine != null ? !medicine.equals(that.medicine) : that.medicine != null) return false;
        return status == that.status;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int result = (int) (detailId ^ (detailId >>> 32));
        result = 31 * result + quantityPack;
        result = 31 * result + (prescription != null ? prescription.hashCode() : 0);
        result = 31 * result + (medicine != null ? medicine.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "PrescriptionDetail{" +
                "detailId=" + detailId +
                ", quantityPack=" + quantityPack +
                ", prescription=" + prescription +
                ", medicine=" + medicine +
                ", status=" + status +
                '}';
    }
}
