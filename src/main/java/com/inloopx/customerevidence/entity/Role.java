package com.inloopx.customerevidence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ROLES")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @NotNull
  private int id;

  @Column(name = "NAME")
  @NotNull
  private String name;

  @Column(name = "FK_USER_ID")
  @NotNull
  private int fkUserId;


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


  public int getFkUserId() {
    return fkUserId;
  }

  public void setFkUserId(int fkUserId) {
    this.fkUserId = fkUserId;
  }

}
