package com.example.onlainShop.service.impl;

import com.example.onlainShop.entity.Cart;
import com.example.onlainShop.repasitory.CartRepository;
import com.example.onlainShop.repasitory.CategoryRepository;
import com.example.onlainShop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public Optional<Cart> findCartByUserId(int id) {
        return cartRepository.findCartByUserId(id);
    }

    @Override
    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }
}
