package com.example.onlainShop.service.impl;

import com.example.onlainShop.entity.Cart;
import com.example.onlainShop.entity.Order;
import com.example.onlainShop.repasitory.CartRepository;
import com.example.onlainShop.repasitory.OrderRepository;
import com.example.onlainShop.service.CartService;
import com.example.onlainShop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
   private final OrderRepository orderRepository;

    @Override
    public void addOrder(Order order) {
        orderRepository.save(order);
    }
}
