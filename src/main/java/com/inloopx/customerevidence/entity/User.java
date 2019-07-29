package com.inloopx.customerevidence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="USERS")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @NotNull
  private int id;

  @Column(name = "USERNAME")
  @NotNull
  private String username;

  @Column(name = "PASSWORD")
  @NotNull
  private String password;

  @ManyToOne
  private Photo photo;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id=id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public Photo getFkCustomerId() {
    return photo;
  }

  public void setFkCustomerId(Photo photo) {
    this.photo = photo;
  }

}
