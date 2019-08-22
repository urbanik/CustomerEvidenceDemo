package com.inloopx.customerevidence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PRODUCTS")
public class Product extends BaseEntity {

  @Column(name = "NAME")
  @NotNull
  private String name;

  @Column(name = "DESCRIPTION")
  @NotNull
  private String description;

  @Column(name = "CODE")
  @NotNull
  private String code;

  @Column(name = "PRICE")
  @NotNull
  private double price;

  @Column(name = "PRICE_WITH_VAT")
  @NotNull
  private double priceWithVat;

  @ManyToOne(optional = true)
  @JoinColumn(name = "FK_PHOTO_ID")
  private Photo photo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
