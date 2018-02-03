package com.alinso.stock.entity;

import javax.persistence.Entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="user")
public class User  extends BaseEntity{



    @Column(name = "name")
    @Size(max = 30)
    private String name;

    @Column(name = "username")
    @Size(max = 30)
    private String username;

    @Column(name = "is_active")
    @NotNull
    private int isActive;


    @Column(name = "email")
    @Size(max = 30)
    private String email;

    @Column(name = "tel")
    private Long tel;


    @Column(name = "password")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}