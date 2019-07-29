package com.inloopx.customerevidence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="ORDERS")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @NotNull
  private int id;

  @Column(name = "PRICE")
  private double price;

  @Column(name = "PRICE_WITH_VAT")
  private double priceWithVat;

  @Column(name = "CREATED")
  private java.sql.Timestamp created;

  @Column(name = "UPDATED")
  private java.sql.Timestamp updated;

  @ManyToOne(optional = false)
  @JoinColumn(name = "FK_CUSTOMER_ID")
  private Customer customer;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
  private List<OrderItem> orderItems;


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


  public java.sql.Timestamp getCreated() {
    return created;
  }

  public void setCreated(java.sql.Timestamp created) {
    this.created = created;
  }

  public Timestamp getUpdated() {
    return updated;
  }

  public void setUpdated(Timestamp updated) {
    this.updated = updated;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }
}
