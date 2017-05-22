package com.amur.areas.cart.models.view;


public class OrderViewModel {

    private long id;

    private String userUsername;

    public OrderViewModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }
}
