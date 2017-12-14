package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.math.BigDecimal;

public class Dosage extends Entity {

    private long dosageId;
    private BigDecimal size;
    private String unit;

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
}
