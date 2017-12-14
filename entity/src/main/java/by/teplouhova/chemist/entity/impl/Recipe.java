package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;

import java.time.LocalDate;
import java.util.HashMap;

public class Recipe extends Entity {
    private long recipeId;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private User client;
    private User doctor;
    private boolean isActive;
    private HashMap<Medicine,Integer>  medicineList;
}
