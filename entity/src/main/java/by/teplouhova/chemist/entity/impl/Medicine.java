package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.math.BigDecimal;

public class Medicine extends Entity {
    private long medicineId;
    private String name;
    private BigDecimal price;
    private int quantityPackages;
    private boolean isNeedRecipe;
    private Medicine analog;
    private int quantityInPackage;
    private ReleaseForm form;
    private Producer producer;

    
}
