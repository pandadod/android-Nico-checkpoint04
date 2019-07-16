package com.example.checkpoint04.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;

    private String email;

    private String password;

    private List<RecipeFav> recipeFavs = new ArrayList<>();

    public User() {
    }

    public User(Long idUser, String mail, String password, List<RecipeFav> recipeFavList) {
        this.id = idUser;
        this.email = mail;
        this.password = password;
        this.recipeFavs = recipeFavList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<RecipeFav> getRecipeFavs() {
        return recipeFavs;
    }

    public void setRecipeFavs(List<RecipeFav> recipeFavs) {
        this.recipeFavs = recipeFavs;
    }
}
