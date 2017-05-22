package com.amur.areas.subcategories.controllers;

import com.amur.areas.categories.models.view.CategoryAndSubcategoriesView;
import com.amur.areas.categories.service.CategoryService;
import com.amur.areas.subcategories.models.binding.SubcategoryBindingModel;
import com.amur.areas.subcategories.models.binding.SubcategoryEditBindingModel;
import com.amur.areas.subcategories.models.view.SubcategoryView;
import com.amur.areas.subcategories.models.view.SubcategoryWithProductsView;
import com.amur.areas.subcategories.service.SubcategoryService;
import com.amur.constants.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SubcategoryController {

    private final SubcategoryService subcategoryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/admin/categories/{id}/subcategories/add")
    public String getAddSubcategoryPage(Model model, @PathVariable long id){
        CategoryAndSubcategoriesView category = this.categoryService.getById(id);
        model.addAttribute("category", category);
        if(!model.containsAttribute("subcategory")){
            model.addAttribute("subcategory", new SubcategoryBindingModel());
        }
        model.addAttribute("view", "/admin/add-subcategory");

        return Path.BASE_LAYOUT;
    }

    @PostMapping("/admin/categories/{id}/subcategories/add")
    public String addSubcategory(Model model, @ModelAttribute SubcategoryBindingModel subcategoryBindingModel, @PathVariable long id){
        model.addAttribute("subcategory", subcategoryBindingModel);
        model.addAttribute("view", "/admin/add-subcategory");
        this.subcategoryService.create(subcategoryBindingModel, id);
        return "redirect:/admin/categories/{id}/subcategories";
    }

    @GetMapping("/admin/categories/{categoryId}/subcategories/edit/{subcategoryId}")
    public String getEditSubcategoryPage(Model model,
//                                         @PathVariable(value = "categoryId") long categoryId,
                                         @PathVariable(value = "subcategoryId") long subcategoryId){
        SubcategoryEditBindingModel subcategory = this.subcategoryService.getByIdToEdit(subcategoryId);
        model.addAttribute("subcategory", subcategory);
        model.addAttribute("view", "/admin/edit-subcategory");
        return Path.BASE_LAYOUT;
    }

    @PostMapping("/admin/categories/{categoryId}/subcategories/edit/{subcategoryId}")
    public String editSubcategory(Model model,
                                  @ModelAttribute SubcategoryEditBindingModel subcategoryEditBindingModel,
                                  @PathVariable(value = "subcategoryId") long subcategoryId){
        subcategoryEditBindingModel.setId(subcategoryId);
        this.subcategoryService.editSubcategory(subcategoryEditBindingModel);
        model.addAttribute("view", "/admin/edit-subcategory");
        return "redirect:/admin/categories/{categoryId}/subcategories";
    }

    @GetMapping("/admin/categories/{id}/subcategories/details/{subcategoryId}")
    public String getSubcategoryDetailsPage(Model model,
                                            @PathVariable(value = "subcategoryId") long subcategoryId){
        SubcategoryWithProductsView subcategory = this.subcategoryService.getSubcategoryWithProducts(subcategoryId);
        model.addAttribute("subcategory", subcategory);
        model.addAttribute("view", "/admin/subcategory-details");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/admin/subcategories/delete/{id}")
    public String getDeleteSubcategoryPage(Model model,
                                           @PathVariable() long id){
        SubcategoryView subcategory = this.subcategoryService.getByIdToDelete(id);
        model.addAttribute("subcategory", subcategory);
        model.addAttribute("view", "/admin/delete-subcategory");
        return Path.BASE_LAYOUT;
    }

    @PostMapping("/admin/subcategories/delete/{id}")
    public String deleteSubcategory(Model model,
                                    @ModelAttribute SubcategoryView subcategoryView,
                                    @PathVariable() long id){
        subcategoryView.setId(id);
        this.subcategoryService.delete(subcategoryView);
        return "redirect:/admin/categories";
    }

}
