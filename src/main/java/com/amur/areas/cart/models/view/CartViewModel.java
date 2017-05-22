package com.amur.areas.cart.models.view;


import com.amur.areas.products.models.view.ProductDetailsView;
import com.amur.areas.users.models.view.UserSimpleView;

import java.util.List;

public class CartViewModel {

    private long id;

    private UserSimpleView user;

    private List<ProductDetailsView> products;

    public CartViewModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserSimpleView getUser() {
        return user;
    }

    public void setUser(UserSimpleView user) {
        this.user = user;
    }

    public List<ProductDetailsView> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetailsView> products) {
        this.products = products;
    }

}
