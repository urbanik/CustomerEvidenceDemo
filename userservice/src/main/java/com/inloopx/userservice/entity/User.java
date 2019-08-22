package com.inloopx.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity{

  @Column(name = "USERNAME")
  @NotNull
  private String username;

  @Column(name = "PASSWORD")
  @NotNull
  private String password;

  @ManyToOne(optional = true)
  @JoinColumn(name = "ROLE_ID")
  private Role role;


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
