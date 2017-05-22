package com.amur.areas.cart.entity;


import com.amur.areas.products.entity.Product;
import com.amur.areas.users.entities.BasicUser;
import com.amur.areas.users.entities.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private BasicUser user;

    @ManyToMany
    @JoinTable(name = "products_carts",
    joinColumns = @JoinColumn(name = "cart_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BasicUser getUser() {
        return user;
    }

    public void setUser(BasicUser user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
