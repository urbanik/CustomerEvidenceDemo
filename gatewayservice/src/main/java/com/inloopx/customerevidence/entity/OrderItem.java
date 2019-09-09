package com.inloopx.customerevidence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @NotNull
  private int id;

  @Column(name = "COUNT")
  @NotNull
  private long count;

  @Column(name = "PRICE")
  @NotNull
  private double price;

  @Column(name = "PRICE_WITH_VAT")
  @NotNull
  private double priceWithVat;

  @ManyToOne
  @JoinColumn(name = "FK_PRODUCT_ID")
  @NotNull
  private Product product;

  @ManyToOne
  @JoinColumn(name = "FK_ORDER_ID")
  @NotNull
  private Order order;

  public OrderItem() {
  }

  public OrderItem(@NotNull Product product) {
    this.product = product;
  }

  public int getId() {
    return id;
  }


  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
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


  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }


  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

}
