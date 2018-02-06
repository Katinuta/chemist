package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Medicine extends Entity implements Serializable {
    private long medicineId;
    private String name;
    private BigDecimal price;
    private int quantityPackages;
    private int quantityInPackage;
    private boolean isNeedRecipe;
    private Medicine analog;
    private ReleaseForm releaseForm;
    private Producer producer;
    private Dosage dosage;
    private UnitInPackage unitInPackage;
    private boolean isDeleted;

    public Medicine() {
//        analog=new Medicine();
        dosage=new Dosage();
//        releaseForm=new ReleaseForm();
//        producer=new Producer();
    }

    public Medicine(long medicineId) {
        this.medicineId = medicineId;
//        analog=new Medicine();
        dosage=new Dosage();
//        releaseForm=new ReleaseForm();
//        producer=new Producer();
    }



    public long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityPackages() {
        return quantityPackages;
    }

    public void setQuantityPackages(int quantityPackages) {
        this.quantityPackages = quantityPackages;
    }

    public int getQuantityInPackage() {
        return quantityInPackage;
    }

    public void setQuantityInPackage(int quantityInPackage) {
        this.quantityInPackage = quantityInPackage;
    }

    public boolean getIsNeedRecipe() {
        return isNeedRecipe;
    }

    public void setNeedRecipe(boolean needRecipe) {
        isNeedRecipe = needRecipe;
    }

    public Medicine getAnalog() {
        return analog;
    }

    public void setAnalog(Medicine analog) {
        this.analog = analog;
    }

    public ReleaseForm getReleaseForm() {
        return releaseForm;
    }

    public void setReleaseForm(ReleaseForm releaseForm) {
        this.releaseForm = releaseForm;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public UnitInPackage getUnitInPackage() {
        return unitInPackage;
    }

    public void setUnitInPackage(UnitInPackage unitInPackage) {
        this.unitInPackage = unitInPackage;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

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
