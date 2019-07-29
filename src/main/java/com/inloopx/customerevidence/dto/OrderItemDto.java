package com.inloopx.customerevidence.dto;

import javax.validation.constraints.NotNull;

public class OrderItemDto {
    private int id;

    @NotNull
    private long amount;

    private double price;

    private double priceWithVat;

    @NotNull
    private ProductDtoGETById product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long count) {
        this.amount = count;
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

    public ProductDtoGETById getProduct() {
        return product;
    }

    public void setProduct(ProductDtoGETById product) {
        this.product = product;
    }

}

