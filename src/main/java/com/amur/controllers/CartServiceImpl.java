package com.amur.controllers;

import com.amur.areas.cart.entity.Cart;
import com.amur.areas.cart.models.view.CartViewModel;
import com.amur.areas.cart.models.view.OrderDetailsView;
import com.amur.areas.cart.models.view.OrderViewModel;
import com.amur.areas.cart.repository.CartRepository;
import com.amur.areas.cart.service.CartService;
import com.amur.areas.products.entity.Product;
import com.amur.areas.products.models.view.ProductDetailsView;
import com.amur.areas.products.service.ProductService;
import com.amur.areas.users.models.view.UserSimpleView;
import com.amur.areas.users.userService.BasicUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final ModelMapper modelMapper;

    private final ProductService productService;

    @Autowired
    private BasicUserService basicUserService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper, ProductService productService) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }


    @Override
    public CartViewModel addProductToCart(long id, CartViewModel cartViewModel) {
        ProductDetailsView productDetailsView = this.productService.showProduct(id);
        if (cartViewModel.getProducts().isEmpty()) {
            cartViewModel = new CartViewModel();
        }

        cartViewModel.getProducts().add(productDetailsView);

        return cartViewModel;
    }

    @Override
    public void create(CartViewModel cartViewModel, String username) {
        UserSimpleView userSimpleView = this.basicUserService.findOneByUsername(username);
        cartViewModel.setUser(userSimpleView);
        List<ProductDetailsView> productDetailsViews = cartViewModel.getProducts();
        for (ProductDetailsView productDetailsView : productDetailsViews) {
            long productId = productDetailsView.getId();
            Integer orderedQuantity = productDetailsView.getQuantity();
            this.productService.setOrderQuantity(orderedQuantity, productId);
        }
        Cart cart = this.modelMapper.map(cartViewModel, Cart.class);
        this.cartRepository.saveAndFlush(cart);
    }

    @Override
    public List<OrderViewModel> findAll() {
        List<Cart> carts = this.cartRepository.findAll();
        List<OrderViewModel> orders = new ArrayList<>();
        for (Cart cart : carts) {
            OrderViewModel cartViewModel = this.modelMapper.map(cart, OrderViewModel.class);
            orders.add(cartViewModel);
        }
        return orders;
    }

    @Override
    public OrderDetailsView getOrderById(long id) {
        Cart cart = this.cartRepository.findOne(id);
        OrderDetailsView orderDetailsView = this.modelMapper.map(cart, OrderDetailsView.class);
        return orderDetailsView;
    }

}
