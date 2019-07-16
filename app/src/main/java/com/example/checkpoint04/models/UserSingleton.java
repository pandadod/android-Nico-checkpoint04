package com.example.checkpoint04.models;

import java.util.List;

public class UserSingleton {

    private static UserSingleton instance;
    private User user;

    private UserSingleton() {
    }

    public static UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void initiateUser(Long idUser, String mail, String password, List<RecipeFav> recipeFavList) {
        user = new User(idUser, mail, password, recipeFavList);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
