package com.amur.controllers;

import com.amur.areas.cart.models.view.CartViewModel;
import com.amur.areas.cart.models.view.OrderDetailsView;
import com.amur.areas.cart.models.view.OrderViewModel;
import com.amur.areas.cart.service.CartService;
import com.amur.areas.users.models.view.UserDetailsView;
import com.amur.areas.users.userService.BasicUserService;
import com.amur.constants.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BasicUserService basicUserService;

    @GetMapping("orders")
    public String getOrdersPage(Model model){
        List<OrderViewModel> orders = this.cartService.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("title", "Orders");
        model.addAttribute("view", "/admin/orders/orders-all");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("order/details/{id}")
    public String getOrderDetailsPage(Model model, @PathVariable long id){
        OrderDetailsView order = this.cartService.getOrderById(id);
        String username = order.getUserUsername();
        UserDetailsView user = this.basicUserService.getOneByUsername(username);
        model.addAttribute("order", order);
        model.addAttribute("title", "Order Details");
        model.addAttribute("user", user);
        model.addAttribute("view", "/admin/orders/order-details");
        return Path.BASE_LAYOUT;
    }

}
