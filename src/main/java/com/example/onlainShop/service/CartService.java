package com.example.onlainShop.service;

import com.example.onlainShop.entity.Cart;

import java.util.Optional;

public interface CartService {
    Optional<Cart> findCartByUserId(int id);
    void addCart(Cart cart);

}
