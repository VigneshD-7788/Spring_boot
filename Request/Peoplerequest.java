package com.Springboot_web_rest.Request;

import java.util.List;

public class Peoplerequest {
    Integer id;
    String email;
    String password;
    String token;
    String name;
    String city_name;
    List<String> account_name;

    public List<String> getAccount_name() {
        return account_name;
    }
    public void setAccount_name(List<String> account_name) {
        this.account_name = account_name;
    }

    public String getCity_name() {
        return city_name;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
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
