package com.amur.areas.categories.service;


import com.amur.areas.categories.entity.Category;
import com.amur.areas.categories.models.binding.CategoryAndSubcategoriesBindingModel;
import com.amur.areas.categories.models.binding.CategoryBindingModel;
import com.amur.areas.categories.models.view.CategoryModel;
import com.amur.areas.categories.models.view.CategoryAndSubcategoriesView;

import java.util.List;

public interface CategoryService {

    List<CategoryAndSubcategoriesView> getAllCategoriesWithSubcategories();

//    CategoryAndSubcategoriesBindingModel getCategoryToAddSubcategory(long id);

    List<CategoryBindingModel> getAllCategories();

//    Set<String> getCategoriesNames();

    Category findOneByName(String categoryName);

    CategoryAndSubcategoriesView getById(long id);

    CategoryBindingModel getByIdToEdit(long id);

    void create(CategoryModel categoryBindingModel);

    void editCategory(CategoryBindingModel categoryBindingModel);

    void delete(CategoryBindingModel categoryBindingModel);

    String getCategoryNameById(long id);
}
