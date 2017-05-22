package com.amur.areas.cart.service;


import com.amur.areas.cart.models.view.CartViewModel;
import com.amur.areas.cart.models.view.OrderDetailsView;
import com.amur.areas.cart.models.view.OrderViewModel;

import java.util.List;

public interface CartService {

    CartViewModel addProductToCart(long id, CartViewModel cartViewModel);

    void create(CartViewModel cartViewModel, String username);

    List<OrderViewModel> findAll();

    OrderDetailsView getOrderById(long id);


}
