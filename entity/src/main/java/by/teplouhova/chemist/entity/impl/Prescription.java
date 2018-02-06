package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Prescription extends Entity {
    private long prescriptionId;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private User client;
    private User doctor;
    private PrescriptionStatus status;
    private List<PrescriptionDetail> details;

    public Prescription() {
        details=new ArrayList<>();
        status=PrescriptionStatus.ACTIVE;
        client=new User();
        doctor=new User();
    }

    public Prescription(long prescriptionId) {
        this.prescriptionId = prescriptionId;
        details=new ArrayList<>();
        status=PrescriptionStatus.ACTIVE;
    }

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

    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    public void setDetails(PrescriptionDetail detail){
        details.add(detail);
    }

    public Iterator<PrescriptionDetail> getDetailsIterator() {
        return details.iterator();
    }
     public boolean isEmptyDetails(){
        return details.isEmpty();
     }

//     public Stream<PrescriptionDetail> getDetailSStream(){
//         return details.stream();
//     }
//
    public List<PrescriptionDetail> getDetails() {
        return details;
    }

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
