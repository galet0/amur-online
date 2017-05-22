package com.amur.areas.products.controllers;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public String search(Model model,
                         @PageableDefault(size = 12) Pageable pageable,
                         @ModelAttribute ProductView productView,
                         @RequestParam(value = "name", required = false) String name) {

        name = name == null ? "" : name;

        Page<ProductView> products = this.productService.findAllByName(name, pageable);

        if(!model.containsAttribute("product")){
            model.addAttribute("product", productView);
        }

        model.addAttribute("view", "/products/products-all");
        model.addAttribute("products", products);
        model.addAttribute("title", "Search");
        return Path.BASE_LAYOUT;
    }
}
