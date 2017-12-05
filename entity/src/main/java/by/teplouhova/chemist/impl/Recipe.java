package by.teplouhova.chemist.impl;

import by.teplouhova.chemist.Entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class Recipe extends Entity {
    private long recipeId;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private long clientId;
    private long doctorId;
    private boolean isActive;
    private HashMap<Long,Integer>  medicineList;
}
