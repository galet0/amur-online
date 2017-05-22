package com.amur.areas.categories.models.view;


import com.amur.areas.subcategories.models.view.SubcategoryView;

import java.util.Set;

public class CategoryAndSubcategoriesView {

    private long id;

    private String name;

    private Set<SubcategoryView> subcategories;

    public CategoryAndSubcategoriesView() {
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

    public Set<SubcategoryView> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<SubcategoryView> subcategories) {
        this.subcategories = subcategories;
    }
}
