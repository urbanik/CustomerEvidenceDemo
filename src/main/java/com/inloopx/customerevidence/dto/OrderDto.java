package com.inloopx.customerevidence.dto;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class OrderDto {

    private int id;

    private double price;

    private double priceWithVat;

    private java.sql.Timestamp date;

    private java.sql.Timestamp updated;

    @NotNull
    private CustomerDtoGETById customer;

    private List<OrderItemDto> orderItems;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getPriceWithVat() {
        return priceWithVat;
    }

    public void setPriceWithVat(double priceWithVat) {
        this.priceWithVat = priceWithVat;
    }


    public java.sql.Timestamp getDate() {
        return date;
    }

    public void setDate(java.sql.Timestamp date) {
        this.date = date;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public CustomerDtoGETById getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDtoGETById customer) {
        this.customer = customer;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

}
