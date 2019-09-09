package com.inloopx.customerevidence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PHOTOS")
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  @NotNull
  private long id;

  @Column(name = "NAME")
  @NotNull
  private String name;

  @Column(name = "RELATIVE_PATH")
  @NotNull
  private String path;

  public Photo() {
  }

  public Photo(@NotNull String name, @NotNull String path) {
    this.name = name;
    this.path = path;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

}
