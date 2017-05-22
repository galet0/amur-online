package com.amur.areas.categories.entity;

import com.amur.areas.subcategories.entity.Subcategory;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", targetEntity = Subcategory.class, cascade = CascadeType.ALL)
    private Set<Subcategory> subCategories;

//    @OneToMany(mappedBy = "category", targetEntity = Product.class, cascade = CascadeType.ALL)
//    private Set<Product> products;

    public Category() {
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

    public Set<Subcategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<Subcategory> subCategories) {
        this.subCategories = subCategories;
    }

//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }
}
