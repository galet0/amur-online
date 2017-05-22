package com.amur.areas.categories.controllers;

import com.amur.areas.categories.models.binding.CategoryBindingModel;
import com.amur.areas.categories.models.view.CategoryModel;
import com.amur.areas.categories.models.view.CategoryAndSubcategoriesView;
import com.amur.areas.categories.service.CategoryService;
import com.amur.areas.subcategories.models.view.SubcategoryView;
import com.amur.constants.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String getAllCategoriesPage(Model model){
        if(!model.containsAttribute("category")){
            model.addAttribute("category", new CategoryModel());
        }
        List<CategoryBindingModel> categories = this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categories);
        model.addAttribute("title", "Categories");
        model.addAttribute("view", "/categories/categories-all");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("add")
    public String getAddCategoryPage(Model model){
        if(!model.containsAttribute("category")){
            model.addAttribute("category", new CategoryModel());
        }
        model.addAttribute("title", "Add Category");
        model.addAttribute("type", "Добави");
        model.addAttribute("view", "/categories/categories-all");
        return Path.BASE_LAYOUT;
    }

    @PostMapping("add")
    public String addCategory(Model model, @ModelAttribute CategoryModel category){
        model.addAttribute("category", category);
        model.addAttribute("title", "Add Category");
        model.addAttribute("type", "Добави");
        model.addAttribute("view", "/categories/categories-all");
        this.categoryService.create(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("{id}/subcategories")
    public String getCategoryDetailsPage(Model model, @PathVariable long id){
        if(!model.containsAttribute("subcategory")){
            model.addAttribute("subcategory", new SubcategoryView());
        }
        CategoryAndSubcategoriesView category = this.categoryService.getById(id);
        model.addAttribute("category", category);
        model.addAttribute("title", "Category details");
        model.addAttribute("view", "/categories/category-details");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("edit/{id}")
    public String getEditCategoryPage(Model model, @PathVariable long id){
        CategoryBindingModel category = this.categoryService.getByIdToEdit(id);
        model.addAttribute("category", category);
        model.addAttribute("title", "Edit Category");
        model.addAttribute("view", "/admin/edit-category");
        return Path.BASE_LAYOUT;
    }

    @PostMapping("edit/{id}")
    public String editCategory(Model model, @ModelAttribute CategoryBindingModel categoryBindingModel, @PathVariable long id){
        categoryBindingModel.setId(id);
        this.categoryService.editCategory(categoryBindingModel);
        model.addAttribute("title", "Edit Category");
        model.addAttribute("view", "/admin/edit-category");
        return "redirect:/admin/categories";
    }

    @GetMapping("delete/{id}")
    public String getDeleteCategoryPage(Model model, @PathVariable long id){
        CategoryAndSubcategoriesView category = this.categoryService.getById(id);
        model.addAttribute("category", category);
        model.addAttribute("title", "Delete Category");
        model.addAttribute("view", "/admin/delete-category");
        return Path.BASE_LAYOUT;
    }

    @PostMapping("delete/{id}")
    public String deleteCategory(@ModelAttribute CategoryBindingModel categoryBindingModel, @PathVariable long id){
        categoryBindingModel.setId(id);
        this.categoryService.delete(categoryBindingModel);
        return "redirect:/admin/categories";
    }
}
