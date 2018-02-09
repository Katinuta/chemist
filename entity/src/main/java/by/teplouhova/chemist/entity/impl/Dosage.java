package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.math.BigDecimal;

/**
 * The Class Dosage.
 */
public class Dosage extends Entity {

    /** The dosage id. */
    private long dosageId;

    /** The size. */
    private BigDecimal size;

    /** The unit. */
    private String unit;

    /**
     * Instantiates a new dosage.
     */
    public Dosage() {
    }

    /**
     * Instantiates a new dosage.
     *
     * @param size the size
     * @param unit the unit
     */
    public Dosage(BigDecimal size, String unit) {
        this.size = size;
        this.unit = unit;
    }

    /**
     * Instantiates a new dosage.
     *
     * @param dosageId the dosage id
     * @param size the size
     * @param unit the unit
     */
    public Dosage(long dosageId, BigDecimal size, String unit) {
        this.dosageId = dosageId;
        this.size = size;
        this.unit = unit;
    }



    /**
     * Gets the dosage id.
     *
     * @return the dosage id
     */
    public long getDosageId() {
        return dosageId;
    }

    /**
     * Sets the dosage id.
     *
     * @param dosageId the new dosage id
     */
    public void setDosageId(long dosageId) {
        this.dosageId = dosageId;
    }

    /**
     * Gets the size.
     *
     * @return the size
     */
    public BigDecimal getSize() {
        return size;
    }

    /**
     * Sets the size.
     *
     * @param size the new size
     */
    public void setSize(BigDecimal size) {
        this.size = size;
    }

    /**
     * Gets the unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the unit.
     *
     * @param unit the new unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
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

        Dosage dosage = (Dosage) o;

        if (size != null ? !size.equals(dosage.size) : dosage.size != null) return false;
        return unit != null ? unit.equals(dosage.unit) : dosage.unit == null;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int result = size != null ? size.hashCode() : 0;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Dosage{" +
                "dosageId=" + dosageId +
                ", size=" + size +
                ", unit='" + unit + '\'' +
                '}';
    }
}