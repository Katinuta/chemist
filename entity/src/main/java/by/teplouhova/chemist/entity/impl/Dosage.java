package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.math.BigDecimal;

public class Dosage extends Entity {

    private long dosageId;
    private BigDecimal size;
    private String unit;

    public Dosage() {
    }

    public Dosage(BigDecimal size, String unit) {
        this.size = size;
        this.unit = unit;
    }

    public Dosage(long dosageId, BigDecimal size, String unit) {
        this.dosageId = dosageId;
        this.size = size;
        this.unit = unit;
    }



    public long getDosageId() {
        return dosageId;
    }

    public void setDosageId(long dosageId) {
        this.dosageId = dosageId;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dosage dosage = (Dosage) o;

        if (size != null ? !size.equals(dosage.size) : dosage.size != null) return false;
        return unit != null ? unit.equals(dosage.unit) : dosage.unit == null;
    }

    @Override
    public int hashCode() {
        int result = size != null ? size.hashCode() : 0;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dosage{" +
                "dosageId=" + dosageId +
                ", size=" + size +
                ", unit='" + unit + '\'' +
                '}';
    }
}
