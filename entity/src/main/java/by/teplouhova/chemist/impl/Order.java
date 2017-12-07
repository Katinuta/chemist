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
    private long issuePointId;
    private HashMap<Long,Integer> medicineList;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getIssuePointId() {
        return issuePointId;
    }

    public void setIssuePointId(long issuePointId) {
        this.issuePointId = issuePointId;
    }
}
