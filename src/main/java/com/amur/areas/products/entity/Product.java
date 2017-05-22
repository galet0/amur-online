package com.amur.areas.products.entity;



import com.amur.areas.brands.entity.Brand;
import com.amur.areas.cart.entity.Cart;
import com.amur.areas.categories.entity.Category;
import com.amur.areas.subcategories.entity.Subcategory;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private Subcategory subcategory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @Column(name = "size")
    private Double size;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "length")
    private Float length;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "order_quatity")
    private Integer quantity;

    public Product() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
