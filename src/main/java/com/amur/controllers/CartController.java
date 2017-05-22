package com.amur.controllers;

import com.amur.areas.cart.models.view.CartViewModel;
import com.amur.areas.cart.service.CartService;
import com.amur.areas.products.models.view.ProductDetailsView;
import com.amur.areas.products.service.ProductService;
import com.amur.areas.users.entities.BasicUser;
import com.amur.areas.users.userService.BasicUserService;
import com.amur.constants.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BasicUserService basicUserService;

    @GetMapping("")
    public String getCartPage(Model model, HttpSession session){
        List<ProductDetailsView> products = (List<ProductDetailsView>) session.getAttribute("products");
        session.setAttribute("products", products);
        model.addAttribute("view", "/cart/shopping-cart");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/buy/{id}")
    public String addProductToCart(Model model, @PathVariable long id, HttpSession session){
        List<ProductDetailsView> products = (List<ProductDetailsView>) session.getAttribute("products");

        ProductDetailsView productDetailsView = this.productService.showProduct(id);

        if(products == null){
            products = new ArrayList<>();
            productDetailsView.setQuantity(1);
            products.add(productDetailsView);
            session.setAttribute("grandtotal", getGrandTotal(products));
        } else {
            boolean flag = false;

            for (ProductDetailsView product : products) {
                if(id == product.getId()) {
                    product.setQuantity(product.getQuantity() + 1);
                    flag = true;
                    session.setAttribute("grandtotal", getGrandTotal(products));
                    break;
                }
            }
            if(flag == false){
                products.add(productDetailsView);
                productDetailsView.setQuantity(1);
                session.setAttribute("grandtotal", getGrandTotal(products));
            }
        }

        session.setAttribute("products", products);
        model.addAttribute("title", "Cart");
        model.addAttribute("view", "/cart/shopping-cart");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/remove/{id}")
    public String removeProductFromCart(Model model, @PathVariable long id, HttpSession session){
        List<ProductDetailsView> products = (List<ProductDetailsView>) session.getAttribute("products");

        ProductDetailsView productDetailsView = this.productService.showProduct(id);

        if(products != null){
            boolean flag = false;

            for (ProductDetailsView product : products) {
                if(id == product.getId()){
                    products.remove(product);
                    flag = true;
                    session.setAttribute("grandtotal", getGrandTotal(products));
                    break;
                }
            }
        }

        session.setAttribute("products", products);
        model.addAttribute("title", "Cart");
        model.addAttribute("view", "/cart/shopping-cart");
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/clear")
    public String clearProductsFromCart(Model model, HttpSession session){
        List<ProductDetailsView> products = (List<ProductDetailsView>) session.getAttribute("products");

        if(products != null){
            products.clear();
        }

        session.setAttribute("products", products);
        model.addAttribute("title", "Cart");
        model.addAttribute("view", "/cart/shopping-cart");
        return Path.BASE_LAYOUT;
    }

    public double getGrandTotal(List<ProductDetailsView> products){
        double grandTotal = 0;

        for (ProductDetailsView product : products) {
            grandTotal += product.getPrice() * product.getQuantity();
        }

        return grandTotal;
    }

    @GetMapping("/order")
    public String getOrderPage(Model model, @ModelAttribute CartViewModel cartViewModel, HttpSession session){
        List<ProductDetailsView> products = (List<ProductDetailsView>) session.getAttribute("products");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if(principal instanceof BasicUser){
            username = ((BasicUser)principal).getUsername();
            cartViewModel.setProducts(products);

            this.cartService.create(cartViewModel, username);
            model.addAttribute("view", "/cart/finalize-order");

        } else {
            //username = principal.toString();
            model.addAttribute("view", "/cart/address-details-for-order");
        }
        model.addAttribute("title", "Cart");
        products.clear();
        return Path.BASE_LAYOUT;
    }

    @GetMapping("/myOrders")
    public String getMyOrdersPage(Model model, @ModelAttribute CartViewModel cartViewModel){

        return Path.BASE_LAYOUT;
    }

}
