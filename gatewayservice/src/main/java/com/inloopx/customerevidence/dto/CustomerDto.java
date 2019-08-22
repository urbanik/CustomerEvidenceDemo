package com.inloopx.customerevidence.dto;

import com.inloopx.customerevidence.entity.Photo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class CustomerDto implements Serializable {

    private int id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String telephone;

    @NotNull
    private String email;


    private Photo photo;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public CustomerDto() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surename) {
        this.surname = surename;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
