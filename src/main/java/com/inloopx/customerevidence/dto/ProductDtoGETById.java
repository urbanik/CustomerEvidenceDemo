package com.inloopx.customerevidence.dto;

import com.inloopx.customerevidence.entity.Photo;

import javax.persistence.ManyToOne;
import java.io.Serializable;

public class ProductDtoGETById implements Serializable {

    private int id;

    private String name;

    private String about;

    private String code;

    private double price;

    private double priceWithVat;

    @ManyToOne
    private Photo photo;

    public ProductDtoGETById() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}
