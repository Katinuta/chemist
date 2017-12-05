package by.teplouhova.chemist.impl;

import by.teplouhova.chemist.Entity;

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

    
}
