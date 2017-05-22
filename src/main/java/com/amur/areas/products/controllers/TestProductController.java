package com.amur.areas.products.controllers;

import com.amur.areas.categories.service.CategoryService;
import com.amur.areas.products.models.view.ProductDetailsView;
import com.amur.areas.products.models.view.ProductView;
import com.amur.areas.products.service.ProductService;
import com.amur.constants.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@Controller
public class TestProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories/{id}/{currentCategory}")
    public String getAllProductsByCategoryPage(Model model,
                                               @PathVariable long id,
                                               @PageableDefault(size = 12) Pageable pageable){
        Page<ProductView> products = this.productService.getAllProductsByCategoryId(pageable, id);

        model.addAttribute("products", products);
        model.addAttribute("title", "Products");
        model.addAttribute("view", "/products/products-all");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/subcategories/{id}/{currentCategories}")
    public String getAllProductsBySubcategoryPage(Model model, @PathVariable long id,
                                                  @PageableDefault(size = 12) Pageable pageable) {

        Page<ProductView> products = this.productService.getAllProductsBySubcategoryId(pageable, id);

        model.addAttribute("products", products);
        model.addAttribute("title", "Products");
        model.addAttribute("view", "/products/products-all");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/products/categories/{categoryId}/subcategories/{subcategoryId}/{subcategoryName}/{id}")
    public String getProductDetailsBySubcategoryPage(Model model,
                                                     @PathVariable(value = "categoryId") long categoryId,
                                                     @PathVariable(value = "subcategoryId") long subcategoryId,
                                                     @PathVariable(value = "id") long id) {
        ProductDetailsView product = this.productService.getProductDetails(categoryId, subcategoryId, id);
        model.addAttribute("product", product);
        model.addAttribute("title", "Products");
        String categoryName = this.categoryService.getCategoryNameById(categoryId);
        switch (categoryName){
            case "Въдици":
                model.addAttribute("view", "/products/rods-details");
                break;
            case "Макари":
                model.addAttribute("view", "/products/reels-details");
                break;
            case "Изкуствени примамки":
                model.addAttribute("view", "/products/artificial-baits-details");
                break;
            case "Захранки":
                model.addAttribute("view", "/products/baits-details");
                break;
            case "Оборудване":
                model.addAttribute("view", "/products/equipment-details");
                break;
            case "Куки":
                model.addAttribute("view", "/products/hooks-details");
                break;
            case "Плувки":
                model.addAttribute("view", "/products/floats-details");
                break;
            case "Аксесоари":
                model.addAttribute("view", "/products/accessories-details");
                break;
            case "Влакно":
                model.addAttribute("view", "/products/fishing-lines-details");
                break;
            case "Облекло":
                model.addAttribute("view", "/products/clothing-details");
                break;
            default:
                model.addAttribute("view", "/products/rods-details");
                break;

        }

        return Path.BASE_LAYOUT;
    }



}
