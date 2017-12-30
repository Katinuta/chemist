package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.time.LocalDate;
import java.util.HashMap;

public class Prescription extends Entity {
    private long prescriptionId;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private User client;
    private User doctor;
    private boolean isActive;
    private HashMap<Medicine,Integer>  medicineList;

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prescription recipe = (Prescription) o;

        if (prescriptionId != recipe.prescriptionId) return false;
        if (isActive != recipe.isActive) return false;
        if (dateBegin != null ? !dateBegin.equals(recipe.dateBegin) : recipe.dateBegin != null) return false;
        if (dateEnd != null ? !dateEnd.equals(recipe.dateEnd) : recipe.dateEnd != null) return false;
        if (client != null ? !client.equals(recipe.client) : recipe.client != null) return false;
        if (doctor != null ? !doctor.equals(recipe.doctor) : recipe.doctor != null) return false;
        return medicineList != null ? medicineList.equals(recipe.medicineList) : recipe.medicineList == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (prescriptionId ^ (prescriptionId >>> 32));
        result = 31 * result + (dateBegin != null ? dateBegin.hashCode() : 0);
        result = 31 * result + (dateEnd != null ? dateEnd.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (doctor != null ? doctor.hashCode() : 0);
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (medicineList != null ? medicineList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", client=" + client +
                ", doctor=" + doctor +
                ", isActive=" + isActive +
                ", medicineList=" + medicineList +
                '}';
    }
}
