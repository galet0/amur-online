package com.amur.areas.categories.serviceImpl;

import com.amur.areas.categories.entity.Category;
import com.amur.areas.categories.models.binding.CategoryBindingModel;
import com.amur.areas.categories.models.view.CategoryModel;
import com.amur.areas.categories.models.view.CategoryAndSubcategoriesView;
import com.amur.areas.subcategories.models.view.SubcategoryView;
import com.amur.areas.categories.repository.CategoryRepository;
import com.amur.areas.categories.service.CategoryService;
import com.amur.areas.subcategories.service.SubcategoryService;
import com.amur.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryAndSubcategoriesView> getAllCategoriesWithSubcategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryAndSubcategoriesView> categoryViews = new ArrayList<>();
        for (Category category : categories) {
            if(category == null){
                throw new ProductNotFoundException();
            }
            CategoryAndSubcategoriesView categoryView = this.modelMapper.map(category, CategoryAndSubcategoriesView.class);
            Set<SubcategoryView> subCategories = this.subcategoryService.getSubCategoriesByCategoryName(categoryView.getName());
            categoryView.setSubcategories(subCategories);
            categoryViews.add(categoryView);
        }
        return categoryViews;
    }

    @Override
    public List<CategoryBindingModel> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        List<CategoryBindingModel> categoryBindingModelList = new ArrayList<>();
        for (Category category : categories) {
            if(category == null){
                throw new ProductNotFoundException();
            }
            CategoryBindingModel categoryBindingModel = this.modelMapper.map(category, CategoryBindingModel.class);
            categoryBindingModelList.add(categoryBindingModel);
        }
        return categoryBindingModelList;
    }

    @Override
    public Category findOneByName(String categoryName) {
        return this.categoryRepository.findOneByName(categoryName);
    }

    @Override
    public CategoryAndSubcategoriesView getById(long id) {
        Category category = this.categoryRepository.findOne(id);
        if(category == null){
            throw new ProductNotFoundException();
        }
        CategoryAndSubcategoriesView categoryAndSubcategoriesView = this.modelMapper.map(category, CategoryAndSubcategoriesView.class);
        return categoryAndSubcategoriesView;
    }

    @Override
    public CategoryBindingModel getByIdToEdit(long id) {
        Category category = this.categoryRepository.findOne(id);
        CategoryBindingModel categoryBindingModel = this.modelMapper.map(category, CategoryBindingModel.class);
        return categoryBindingModel;
    }

    @Override
    public void create(CategoryModel categoryBindingModel) {
        Category category = this.modelMapper.map(categoryBindingModel, Category.class);
        this.categoryRepository.saveAndFlush(category);
    }

    @Transactional
    @Override
    public void editCategory(CategoryBindingModel categoryBindingModel) {
        Category category = this.modelMapper.map(categoryBindingModel, Category.class);
        long id = category.getId();
        String name = category.getName();
        this.categoryRepository.findOneToEdit(id, name);
    }

    @Override
    public void delete(CategoryBindingModel categoryBindingModel) {
        Category category = this.modelMapper.map(categoryBindingModel, Category.class);
        this.categoryRepository.delete(category);
    }

    @Override
    public String getCategoryNameById(long id) {
        String categoryName = this.categoryRepository.findCategoryNameById(id);
        return categoryName;
    }

}
