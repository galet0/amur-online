package com.amur.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getHomePage(Model model){
        Date secondDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date firstDate = calendar.getTime();

        List<ProductView> products = this.productService.getNewProducts(firstDate, secondDate);
        model.addAttribute("products", products);

        model.addAttribute("title", "Home");
        model.addAttribute("view", "home");
        return Path.BASE_LAYOUT;
    }

}
