package com.richard.mintynconsumer.model;


import java.util.Date;
import java.util.UUID;


public class Order {

    private UUID id;
    private String name;
    private String phone;
    private double price;
    private int qty;
    private Product product;
    private Date date;

    public Order() {
    }

    public Order(UUID id, String name, String phone, double price, int qty, Product product, Date date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.price = price;
        this.qty = qty;
        this.product = product;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
