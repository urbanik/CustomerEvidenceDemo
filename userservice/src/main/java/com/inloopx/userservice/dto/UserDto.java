package com.inloopx.userservice.dto;


import javax.validation.constraints.NotNull;

public class UserDto{

  @NotNull
  private String username;

  @NotNull
  private String password;

  private RoleDto role;


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

  public RoleDto getRole() {
    return role;
  }

  public void setRole(RoleDto role) {
    this.role = role;
  }
}
