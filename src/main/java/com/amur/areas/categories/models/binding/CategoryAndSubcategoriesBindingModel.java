package com.amur.areas.categories.models.binding;


import com.amur.areas.subcategories.models.binding.SubcategoryBindingModel;

import java.util.Set;

public class CategoryAndSubcategoriesBindingModel {

    private long id;

    private String name;

    private Set<SubcategoryBindingModel> subcategories;

    public CategoryAndSubcategoriesBindingModel() {
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

    public Set<SubcategoryBindingModel> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<SubcategoryBindingModel> subcategories) {
        this.subcategories = subcategories;
    }
}
