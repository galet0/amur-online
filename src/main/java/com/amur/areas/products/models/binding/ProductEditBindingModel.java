package com.amur.areas.products.models.binding;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

public class ProductEditBindingModel {

    private long id;

    private String name;

    @DecimalMin("0.0")
    private Double price;

    private String image;

    @DecimalMin("0.0")
    private Double size;

    @DecimalMin("0.0")
    private Double weight;

    @DecimalMin("0.0")
    private Float length;

    private String description;

    public ProductEditBindingModel() {
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
}
