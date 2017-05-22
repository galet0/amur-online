package com.amur.areas.products.controllers;

import com.amur.areas.brands.service.BrandService;
import com.amur.areas.products.models.binding.ProductBindingModel;
import com.amur.areas.products.models.binding.ProductEditBindingModel;
import com.amur.areas.products.models.view.ProductDeleteModel;
import com.amur.areas.products.service.ProductService;
import com.amur.areas.subcategories.service.SubcategoryService;
import com.amur.constants.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SubcategoryService subcategoryService;

    @Autowired
    private BrandService brandService;

    @ModelAttribute("brandList")
    public List<String> getBrands(){
        List<String> brands = this.brandService.getAllBrands();
        return brands;
    }

    @GetMapping("/admin/subcategories/{id}/product/add")
    public String getAddProductPage(Model model, @ModelAttribute ProductBindingModel productBindingModel, @PathVariable long id){
        if(!model.containsAttribute("product")){
            model.addAttribute("product", productBindingModel);
        }

        String subcategoryName = this.subcategoryService.getSubcategoryNameById(id);
        String categoryName = this.subcategoryService.getCategoryNameBySubcategoryId(id);
        productBindingModel.setCategoryName(categoryName);
        productBindingModel.setSubcategoryName(subcategoryName);
        model.addAttribute("title", "Add Product");
        model.addAttribute("view", "/admin/add-product");
        return Path.BASE_LAYOUT;
    }

    @PostMapping("/admin/subcategories/{id}/product/add")
    public String addProduct(Model model, @ModelAttribute ProductBindingModel productBindingModel, @PathVariable long id){
        model.addAttribute("title", "Add Product");
        model.addAttribute("view", "/admin/add-product");
        this.productService.create(productBindingModel);
        return "redirect:/admin/categories/{id}/subcategories/details/{id}";
    }

    @GetMapping("/admin/subcategories/{subcategoryId}/product/edit/{productId}")
    public String getEditProductPage(Model model,
//                                     @ModelAttribute ProductEditBindingModel productEditBindingModel,
                                     @PathVariable(value = "subcategoryId") long subcategoryId,
                                     @PathVariable(value = "productId") long productId){
        ProductEditBindingModel product = this.productService.findOneByIdToEdit(productId);
        model.addAttribute("product", product);
        model.addAttribute("title", "Edit Product");
        model.addAttribute("view", "/admin/edit-product");
        return Path.BASE_LAYOUT;
    }

    @PostMapping("/admin/subcategories/{id}/product/edit/{productId}")
    public String updateProduct(Model model,
                                @ModelAttribute ProductEditBindingModel productEditBindingModel,
                                @PathVariable long id,
                                @PathVariable(value = "productId") long productId){
        productEditBindingModel.setId(productId);
        this.productService.updateProduct(productEditBindingModel);
        model.addAttribute("title", "Edit Product");
        model.addAttribute("view", "/admin/edit-product");
        return "redirect:/admin/categories/{id}/subcategories/details/{id}";
    }

    @GetMapping("/admin/subcategories/{id}/product/delete/{productId}")
    public String getDeleteProductPage(Model model,
                                       @PathVariable long productId){
        ProductDeleteModel product = this.productService.findOneToDelete(productId);
        model.addAttribute("product", product);
        model.addAttribute("title", "Delete Product");
        model.addAttribute("view", "/admin/delete-product");
        return Path.BASE_LAYOUT;
    }

    @PostMapping("/admin/subcategories/{id}/product/delete/{productId}")
    public String deleteProduct(@ModelAttribute ProductDeleteModel productDeleteModel,
                                @PathVariable long productId){
        productDeleteModel.setId(productId);
        this.productService.delete(productDeleteModel);
        return "redirect:/admin/categories/{id}/subcategories/details/{id}";
    }
}
