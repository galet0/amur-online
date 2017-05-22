package com.amur.areas.categories.controllers;


import com.amur.areas.categories.models.view.CategoryAndSubcategoriesView;
import com.amur.areas.categories.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryAndSubcategoriesView>> getCategories(){
        List<CategoryAndSubcategoriesView> categories = this.categoryService.getAllCategoriesWithSubcategories();
//        if(categories == null){
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }

        ResponseEntity<List<CategoryAndSubcategoriesView>> responseEntity = new ResponseEntity(categories, HttpStatus.OK);
        return responseEntity;
    }
//
//    @GetMapping("/admin/categories/id/subcategories")
//    public ResponseEntity<CategoryAndSubcategoriesView> getCategoryWithSubcategories(@PathVariable long id){
//        CategoryAndSubcategoriesView categoryWithSubCategories = this.categoryService.getById(id);
//        ResponseEntity<CategoryAndSubcategoriesView> responseEntity = new ResponseEntity(categoryWithSubCategories, HttpStatus.OK);
//        return responseEntity;
//    }
}
