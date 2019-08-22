package com.inloopx.customerevidence.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

    private int id;

    private double price;

    private double priceWithVat;

    private LocalDateTime date;

    private LocalDateTime updated;

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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
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
