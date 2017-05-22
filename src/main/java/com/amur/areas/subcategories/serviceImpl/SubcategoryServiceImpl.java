package com.amur.areas.subcategories.serviceImpl;

import com.amur.areas.categories.entity.Category;
import com.amur.areas.categories.models.view.CategoryAndSubcategoriesView;
import com.amur.areas.categories.service.CategoryService;
import com.amur.areas.subcategories.entity.Subcategory;
import com.amur.areas.subcategories.models.binding.SubcategoryBindingModel;
import com.amur.areas.subcategories.models.binding.SubcategoryEditBindingModel;
import com.amur.areas.subcategories.models.view.SubcategoryView;
import com.amur.areas.subcategories.models.view.SubcategoryWithProductsView;
import com.amur.areas.subcategories.repository.SubcategoryRepository;
import com.amur.areas.subcategories.service.SubcategoryService;
import com.amur.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository, ModelMapper modelMapper) {
        this.subcategoryRepository = subcategoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<SubcategoryView> getSubCategoriesByCategoryName(String category) {
        Set<Subcategory> subCategories = this.subcategoryRepository.findAllByCategoryName(category);
        Set<SubcategoryView> subCategoryViews = new HashSet<>();
        for (Subcategory subcategory : subCategories) {
            if(subcategory == null){
                throw new ProductNotFoundException();
            }
            SubcategoryView subCategoryView = this.modelMapper.map(subcategory, SubcategoryView.class);
            subCategoryViews.add(subCategoryView);
        }
        return subCategoryViews;
    }

    @Override
    public Set<SubcategoryBindingModel> getSubcategoriesByCategoryId(long categoryId) {
        Set<Subcategory> subCategories = this.subcategoryRepository.findAllByCategoryId(categoryId);
        Set<SubcategoryBindingModel> subcategoryBindingModels = new HashSet<>();
        for (Subcategory subcategory : subCategories) {
            if(subcategory == null){
                throw new ProductNotFoundException();
            }
            SubcategoryBindingModel subcategoryBindingModel = this.modelMapper.map(subcategory, SubcategoryBindingModel.class);
            subcategoryBindingModels.add(subcategoryBindingModel);
        }
        return subcategoryBindingModels;
    }

    @Override
    public Subcategory findOneByName(String subcategoryName) {
        return this.subcategoryRepository.findOneByName(subcategoryName);
    }

    @Override
    public SubcategoryView create(SubcategoryBindingModel subcategoryBindingModel, long id) {
        Subcategory subcategory = this.modelMapper.map(subcategoryBindingModel, Subcategory.class);
        CategoryAndSubcategoriesView categoryAndSubcategoriesView = this.categoryService.getById(id);
        Category category = this.modelMapper.map(categoryAndSubcategoriesView, Category.class);
        subcategory.setCategory(category);
        this.subcategoryRepository.saveAndFlush(subcategory);
        SubcategoryView subCategoryView = this.modelMapper.map(subcategory, SubcategoryView.class);
        return subCategoryView;
    }

    @Override
    public SubcategoryEditBindingModel getByIdToEdit(long id) {
        Subcategory subcategory = this.subcategoryRepository.findOne(id);
        SubcategoryEditBindingModel subcategoryBindingModel = this.modelMapper.map(subcategory, SubcategoryEditBindingModel.class);
        return subcategoryBindingModel;
    }

    @Transactional
    @Override
    public void editSubcategory(SubcategoryEditBindingModel subcategoryEditBindingModel) {
        Subcategory subcategory = this.modelMapper.map(subcategoryEditBindingModel, Subcategory.class);
        long id = subcategory.getId();
        String name = subcategory.getName();
        this.subcategoryRepository.findOneToEdit(id, name);
    }

    @Override
    public SubcategoryWithProductsView getSubcategoryWithProducts(long id) {
        Subcategory subcategory = this.subcategoryRepository.findOne(id);
        SubcategoryWithProductsView subcategoryWithProductsView = this.modelMapper.map(subcategory, SubcategoryWithProductsView.class);
        return subcategoryWithProductsView;
    }

    @Override
    public void delete(SubcategoryView subcategoryView) {
        Subcategory subcategory = this.modelMapper.map(subcategoryView, Subcategory.class);
        this.subcategoryRepository.delete(subcategory);
    }

    @Override
    public SubcategoryView getByIdToDelete(long subcategoryId) {
        Subcategory subcategory = this.subcategoryRepository.findOne(subcategoryId);
        SubcategoryView subCategoryView = this.modelMapper.map(subcategory, SubcategoryView.class);
        return subCategoryView;
    }

    @Override
    public String getSubcategoryNameById(long id) {
        String subcategoryName = this.subcategoryRepository.findSubcategoryNameBySubcategoryId(id);
        return subcategoryName;
    }

    @Override
    public String getCategoryNameBySubcategoryId(long id) {
        String categoryName = this.subcategoryRepository.findCategoryNameBySubcategoryId(id);
        return categoryName;
    }

}
