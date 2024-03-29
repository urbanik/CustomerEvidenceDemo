package com.inloopx.customerevidence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="CUSTOMERS")
public class Customer extends BaseEntity implements Serializable {

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "SURNAME")
    @NotNull
    private String surname;

    @Column(name = "PHONE")
    @NotNull
    private String phone;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @OneToOne(optional = true)
    @JoinColumn(name = "FK_PHOTO_ID")
    private Photo photo;

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Customer() {
    }

    public Customer(@NotNull String name, @NotNull String surname, @NotNull String phone, @NotNull String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String priezvisko) {
        this.surname = priezvisko;
    }

    public void setPhone(String telephone) {
        this.phone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
