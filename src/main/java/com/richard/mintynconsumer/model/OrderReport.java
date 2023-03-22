package com.richard.mintynconsumer.model;

import java.util.Date;

public class OrderReport {

    private Date date;
    private int totalOrder;
    private double totalAmount;

    public OrderReport(Date date, int totalOrder, double totalAmount) {
        this.date = date;
        this.totalOrder = totalOrder;
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
