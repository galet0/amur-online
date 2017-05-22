package com.amur.areas.products.controllers;

import com.amur.areas.products.models.view.ProductView;
import com.amur.areas.products.service.ProductService;
import com.amur.constants.Path;
import com.amur.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String getAllProductsPage(Model model,
                                     @PageableDefault(size = 12) Pageable pageable){
        Page<ProductView> products = this.productService.findAll(pageable);

        model.addAttribute("products", products);
        model.addAttribute("title", "Products");
        model.addAttribute("view", "/products/products-all");
        return Path.BASE_LAYOUT;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public String catchProductNotFoundException(Model model){
        model.addAttribute("view","/error/product-not-found");
        return Path.BASE_LAYOUT;
    }

}
