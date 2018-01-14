package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

public class PrescriptionDetail extends Entity {

    private long detailId;
    private int quantityPack;
    private Prescription prescription;
    private Medicine medicine;
    private PrescriptionStatus status;

    public PrescriptionDetail() {
    }

    public PrescriptionDetail(long recordId, int quantity,
                              Prescription prescription, Medicine medicine,
                              PrescriptionStatus status) {
        this.detailId = recordId;
        this.quantityPack = quantity;
        this.prescription = prescription;
        this.medicine = medicine;
        this.status = status;
    }

    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public int getQuantityPack() {
        return quantityPack;
    }

    public void setQuantityPack(int quantityPack) {
        this.quantityPack = quantityPack;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

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

    @Override
    public int hashCode() {
        int result = (int) (detailId ^ (detailId >>> 32));
        result = 31 * result + quantityPack;
        result = 31 * result + (prescription != null ? prescription.hashCode() : 0);
        result = 31 * result + (medicine != null ? medicine.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

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
