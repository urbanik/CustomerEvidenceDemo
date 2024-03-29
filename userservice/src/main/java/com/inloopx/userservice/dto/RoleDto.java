package com.inloopx.userservice.dto;

public class RoleDto {

  private int id;

  private String name;

  public RoleDto() {
  }

  public RoleDto(String name) {
    this.name = name;
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

}
