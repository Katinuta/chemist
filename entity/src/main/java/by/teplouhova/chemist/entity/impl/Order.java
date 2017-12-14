package by.teplouhova.chemist.entity.impl;

import by.teplouhova.chemist.entity.Entity;
import by.teplouhova.chemist.entity.StatusOrderEnum;

import java.time.LocalDate;
import java.util.HashMap;

public class Order extends Entity {
    private long orderId;
    private LocalDate dateCreating;
    private StatusOrderEnum status;
    private User user;
    private long issuePointId;
    private HashMap<Medicine,Integer> medicineList;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDateCreating() {
        return dateCreating;
    }

    public void setDateCreating(LocalDate dateCreating) {
        this.dateCreating = dateCreating;
    }

    public StatusOrderEnum getStatus() {
        return status;
    }

    public void setStatus(StatusOrderEnum status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(long userId) {
        this.user= user;
    }

    public long getIssuePointId() {
        return issuePointId;
    }

    public void setIssuePointId(long issuePointId) {
        this.issuePointId = issuePointId;
    }
}
