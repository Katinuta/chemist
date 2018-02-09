package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;
import jdk.internal.org.objectweb.asm.commons.SerialVersionUIDAdder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class Medicine.
 */
public class Medicine extends Entity {

    /** The medicine id. */
    private long medicineId;

    /** The name. */
    private String name;

    /** The price. */
    private BigDecimal price;

    /** The quantity packages. */
    private int quantityPackages;

    /** The quantity in package. */
    private int quantityInPackage;

    /** The is need recipe. */
    private boolean isNeedRecipe;

    /** The analog. */
    private Medicine analog;

    /** The release form. */
    private ReleaseForm releaseForm;

    /** The producer. */
    private Producer producer;

    /** The dosage. */
    private Dosage dosage;

    /** The unit in package. */
    private UnitInPackage unitInPackage;

    /** The is deleted. */
    private boolean isDeleted;

    /**
     * Instantiates a new medicine.
     */
    public Medicine() {
        dosage=new Dosage();
    }

    /**
     * Instantiates a new medicine.
     *
     * @param medicineId the medicine id
     */
    public Medicine(long medicineId) {
        this.medicineId = medicineId;
        dosage=new Dosage();
    }



    /**
     * Gets the medicine id.
     *
     * @return the medicine id
     */
    public long getMedicineId() {
        return medicineId;
    }

    /**
     * Sets the medicine id.
     *
     * @param medicineId the new medicine id
     */
    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price.
     *
     * @param price the new price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the quantity packages.
     *
     * @return the quantity packages
     */
    public int getQuantityPackages() {
        return quantityPackages;
    }

    /**
     * Sets the quantity packages.
     *
     * @param quantityPackages the new quantity packages
     */
    public void setQuantityPackages(int quantityPackages) {
        this.quantityPackages = quantityPackages;
    }

    /**
     * Gets the quantity in package.
     *
     * @return the quantity in package
     */
    public int getQuantityInPackage() {
        return quantityInPackage;
    }

    /**
     * Sets the quantity in package.
     *
     * @param quantityInPackage the new quantity in package
     */
    public void setQuantityInPackage(int quantityInPackage) {
        this.quantityInPackage = quantityInPackage;
    }

    /**
     * Gets the checks if is need recipe.
     *
     * @return the checks if is need recipe
     */
    public boolean getIsNeedRecipe() {
        return isNeedRecipe;
    }

    /**
     * Sets the need recipe.
     *
     * @param needRecipe the new need recipe
     */
    public void setNeedRecipe(boolean needRecipe) {
        isNeedRecipe = needRecipe;
    }

    /**
     * Gets the analog.
     *
     * @return the analog
     */
    public Medicine getAnalog() {
        return analog;
    }

    /**
     * Sets the analog.
     *
     * @param analog the new analog
     */
    public void setAnalog(Medicine analog) {
        this.analog = analog;
    }

    /**
     * Gets the release form.
     *
     * @return the release form
     */
    public ReleaseForm getReleaseForm() {
        return releaseForm;
    }

    /**
     * Sets the release form.
     *
     * @param releaseForm the new release form
     */
    public void setReleaseForm(ReleaseForm releaseForm) {
        this.releaseForm = releaseForm;
    }

    /**
     * Gets the producer.
     *
     * @return the producer
     */
    public Producer getProducer() {
        return producer;
    }

    /**
     * Sets the producer.
     *
     * @param producer the new producer
     */
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    /**
     * Gets the dosage.
     *
     * @return the dosage
     */
    public Dosage getDosage() {
        return dosage;
    }

    /**
     * Sets the dosage.
     *
     * @param dosage the new dosage
     */
    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    /**
     * Gets the unit in package.
     *
     * @return the unit in package
     */
    public UnitInPackage getUnitInPackage() {
        return unitInPackage;
    }

    /**
     * Sets the unit in package.
     *
     * @param unitInPackage the new unit in package
     */
    public void setUnitInPackage(UnitInPackage unitInPackage) {
        this.unitInPackage = unitInPackage;
    }

    /**
     * Gets the checks if is deleted.
     *
     * @return the checks if is deleted
     */
    public boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

        Medicine medicine = (Medicine) o;

        if (quantityInPackage != medicine.quantityInPackage) return false;
        if (isNeedRecipe != medicine.isNeedRecipe) return false;
        if (name != null ? !name.equals(medicine.name) : medicine.name != null) return false;
        if (price != null ? !price.equals(medicine.price) : medicine.price != null) return false;
        if (releaseForm != null ? !releaseForm.equals(medicine.releaseForm) : medicine.releaseForm != null)
            return false;
        if (producer != null ? !producer.equals(medicine.producer) : medicine.producer != null) return false;
        if (dosage != null ? !dosage.equals(medicine.dosage) : medicine.dosage != null) return false;
        return unitInPackage == medicine.unitInPackage;
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + quantityInPackage;
        result = 31 * result + (isNeedRecipe ? 1 : 0);
        result = 31 * result + (releaseForm != null ? releaseForm.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        result = 31 * result + (unitInPackage != null ? unitInPackage.hashCode() : 0);
        return result;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId=" + medicineId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantityPackages=" + quantityPackages +
                ", quantityInPackage=" + quantityInPackage +
                ", isNeedRecipe=" + isNeedRecipe +
                ", analog=" + analog +
                ", releaseForm=" + releaseForm +
                ", producer=" + producer +
                ", dosage=" + dosage +
                ", unitInPackage=" + unitInPackage +
                '}';
    }
}
