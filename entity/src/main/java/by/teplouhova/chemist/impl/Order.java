package by.teplouhova.chemist.impl;

import by.teplouhova.chemist.Entity;
import by.teplouhova.chemist.StatusOrderEnum;

import java.time.LocalDate;
import java.util.HashMap;

public class Order extends Entity {
    private long orderId;
    private LocalDate dateCreating;
    private StatusOrderEnum status;
    private long userId;
    private HashMap<Long,Integer> medicineList;


}
