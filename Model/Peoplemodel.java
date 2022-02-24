package com.Springboot_web_rest.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="people")

public class Peoplemodel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String email;
    String password;
    String picture;
    String name;
    @Column(insertable = false,updatable = false)
    Double amount;
    String type;
    String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @OneToOne
    @JoinColumn(name="city_id",referencedColumnName = "id")
    Citymodel city;

    @OneToMany
    @JoinColumn(name="user_id")
    List<Socialmodel> media;

    public List<Socialmodel> getMedia() {
        return media;
    }

    public void setMedia(List<Socialmodel> media) {
        this.media = media;
    }

    public Citymodel getCity() {
        return city;
    }

    public void setCity(Citymodel city) {
        this.city = city;
    }
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
