package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.math.BigDecimal;

public class Medicine extends Entity {
    private long medicineId;
    private String name;
    private BigDecimal price;
    private int quantityPackages;
    private boolean isNeedRecipe;
    private long analogId;
    private int quantityInPackage;
    private long releaseFormId;
    private long producerId;

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

    public boolean getIsNeedRecipe() {
        return isNeedRecipe;
    }

    public void setNeedRecipe(boolean needRecipe) {
        isNeedRecipe = needRecipe;
    }

    public long getAnalogId() {
        return analogId;
    }

    public void setAnalogId(long analogId) {
        this.analogId = analogId;
    }

    public int getQuantityInPackage() {
        return quantityInPackage;
    }

    public void setQuantityInPackage(int quantityInPackage) {
        this.quantityInPackage = quantityInPackage;
    }

    public long getReleaseFormId() {
        return releaseFormId;
    }

    public void setReleaseFormId(long releaseFormId) {
        this.releaseFormId = releaseFormId;
    }

    public long getProducerId() {
        return producerId;
    }

    public void setProducerId(long producerId) {
        this.producerId = producerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicine medicine = (Medicine) o;

        if (medicineId != medicine.medicineId) return false;
        if (quantityPackages != medicine.quantityPackages) return false;
        if (isNeedRecipe != medicine.isNeedRecipe) return false;
        if (analogId != medicine.analogId) return false;
        if (quantityInPackage != medicine.quantityInPackage) return false;
        if (releaseFormId != medicine.releaseFormId) return false;
        if (producerId != medicine.producerId) return false;
        if (name != null ? !name.equals(medicine.name) : medicine.name != null) return false;
        return price != null ? price.equals(medicine.price) : medicine.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (medicineId ^ (medicineId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + quantityPackages;
        result = 31 * result + (isNeedRecipe ? 1 : 0);
        result = 31 * result + (int) (analogId ^ (analogId >>> 32));
        result = 31 * result + quantityInPackage;
        result = 31 * result + (int) (releaseFormId ^ (releaseFormId >>> 32));
        result = 31 * result + (int) (producerId ^ (producerId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId=" + medicineId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantityPackages=" + quantityPackages +
                ", isNeedRecipe=" + isNeedRecipe +
                ", analogId=" + analogId +
                ", quantityInPackage=" + quantityInPackage +
                ", releaseFormId=" + releaseFormId +
                ", producerId=" + producerId +
                '}';
    }
}
