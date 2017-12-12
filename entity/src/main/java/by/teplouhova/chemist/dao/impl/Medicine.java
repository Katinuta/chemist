package by.teplouhova.chemist.dao.impl;

import by.teplouhova.chemist.Entity;

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
