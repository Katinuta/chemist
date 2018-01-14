package by.teplouhova.chemist.creator;

import by.teplouhova.chemist.entity.impl.*;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class MedicineCreator {
    public Medicine createMedicine(String medicineId, String name, String price, String quantityPackages,
                                   String analogId, String unitDosage, String dosageSize, String quantityInPack,
                                   String unitInPack, boolean needPrescription, String releaseFormId,
                                   String producerId) {
        Medicine medicine = new Medicine();
        medicine.setMedicineId(Long.parseLong(medicineId));
        medicine.setName(name);
        medicine.setPrice(new BigDecimal(Double.parseDouble(price)));
        medicine.setQuantityPackages(Integer.parseInt(subStringToInteger(quantityPackages)));
        medicine.setQuantityInPackage(Integer.parseInt(subStringToInteger(quantityInPack)));
        Dosage dosage = new Dosage();
        dosage.setUnit(unitDosage);
        dosage.setSize(new BigDecimal(Double.parseDouble(dosageSize)));
        medicine.setDosage(dosage);
        medicine.setNeedRecipe(needPrescription);
        medicine.setUnitInPackage(UnitInPackage.valueOf(unitInPack.toUpperCase()));
        medicine.setProducer(new Producer(Long.parseLong(producerId)));
        Medicine analog = analogId.isEmpty() ? new Medicine() : new Medicine(Long.parseLong(analogId));
        medicine.setAnalog(analog);
        medicine.setReleaseForm(new ReleaseForm(Long.parseLong(releaseFormId)));
        return medicine;
    }

    public Medicine createMedicine(String name, String price, String quantityPackages,
                                   String analogId, String unitDosage, String dosageSize, String quantityInPack,
                                   String unitInPack, boolean needPrescription, String releaseFormId,
                                   String producerId) {
        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setPrice(new BigDecimal(Double.parseDouble(price)));

        medicine.setQuantityPackages(Integer.parseInt(subStringToInteger(quantityPackages)));
        medicine.setQuantityInPackage(Integer.parseInt(subStringToInteger(quantityInPack)));
        Dosage dosage = new Dosage();
        dosage.setUnit(unitDosage);
        dosage.setSize(new BigDecimal(Double.parseDouble(dosageSize)));
        medicine.setDosage(dosage);
        medicine.setNeedRecipe(needPrescription);
        medicine.setUnitInPackage(UnitInPackage.valueOf(unitInPack.toUpperCase()));
        medicine.setProducer(new Producer(Long.parseLong(producerId)));
        Medicine analog = analogId.isEmpty() ? new Medicine() : new Medicine(Long.parseLong(analogId));
        medicine.setAnalog(analog);
        medicine.setReleaseForm(new ReleaseForm(Long.parseLong(releaseFormId)));
        return medicine;
    }

    private String subStringToInteger(String quantity) {
        String substring = null;

        if (quantity.contains(".")) {
            int indexEnd = quantity.indexOf(".");
            substring = quantity.substring(0, indexEnd);
        }

        return substring == null ? quantity : substring;
    }
}
