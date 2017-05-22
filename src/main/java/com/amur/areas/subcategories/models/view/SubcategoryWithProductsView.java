package com.amur.areas.subcategories.models.view;


import com.amur.areas.products.models.view.ProductView;

import java.util.Set;

public class SubcategoryWithProductsView {

    private long id;

    private String name;

    private long categoryId;

    private Set<ProductView> products;

    public SubcategoryWithProductsView() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<ProductView> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductView> products) {
        this.products = products;
    }
}
