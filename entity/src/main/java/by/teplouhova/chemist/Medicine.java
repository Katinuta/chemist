package by.teplouhova.chemist;

import java.math.BigDecimal;

public class Medicine extends Entity {
    private long medicineId;
    private String name;
    private BigDecimal price;
    private int quantityPackages;
    private boolean isNeedRecipe;
    private Medicine analog;
    private int quantityInPackage;
    
}
