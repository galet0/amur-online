package com.amur.areas.subcategories.entity;

import com.amur.areas.categories.entity.Category;
import com.amur.areas.products.entity.Product;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subcategories")
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "subcategory", targetEntity = Product.class)
    private Set<Product> products;

    public Subcategory() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
