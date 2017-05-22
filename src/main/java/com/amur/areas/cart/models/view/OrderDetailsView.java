package com.amur.areas.cart.models.view;


import com.amur.areas.products.models.view.ProductDetailsView;

import java.util.List;

public class OrderDetailsView {

    private long id;

    private String userUsername;

    private List<ProductDetailsView> products;

    public OrderDetailsView() {
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

    public List<ProductDetailsView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetailsView> products) {
        this.products = products;
    }
}
