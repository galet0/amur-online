package com.amur.areas.subcategories.service;


import com.amur.areas.subcategories.entity.Subcategory;
import com.amur.areas.subcategories.models.binding.SubcategoryBindingModel;
import com.amur.areas.subcategories.models.binding.SubcategoryEditBindingModel;
import com.amur.areas.subcategories.models.view.SubcategoryView;
import com.amur.areas.subcategories.models.view.SubcategoryWithProductsView;

import java.util.Set;

public interface SubcategoryService {

    Set<SubcategoryView> getSubCategoriesByCategoryName(String category);

    Set<SubcategoryBindingModel> getSubcategoriesByCategoryId(long categoryId);

//    Set<String> getSubCategoriesNames(String category);

    Subcategory findOneByName(String subcategoryName);

    SubcategoryView create(SubcategoryBindingModel subcategoryBindingModel, long id);

    SubcategoryEditBindingModel getByIdToEdit(long id);

    void editSubcategory(SubcategoryEditBindingModel subcategoryEditBindingModel);

    SubcategoryWithProductsView getSubcategoryWithProducts(long id);

    void delete(SubcategoryView subCategoryView);

    SubcategoryView getByIdToDelete(long subcategoryId);

    String getSubcategoryNameById(long id);

    String getCategoryNameBySubcategoryId(long id);

}
