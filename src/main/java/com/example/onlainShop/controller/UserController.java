package com.example.onlainShop.controller;

import com.example.onlainShop.entity.Cart;
import com.example.onlainShop.entity.Product;
import com.example.onlainShop.entity.User;
import com.example.onlainShop.service.impl.CartServiceImpl;
import com.example.onlainShop.service.impl.ProductServiceImpl;
import com.example.onlainShop.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashSet;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final ProductServiceImpl productServiceImpl;
    private final CartServiceImpl cartServiceImpl;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        Cart cart = Cart.builder()
                .user(user)
                .productList(new LinkedHashSet<>())
                .build();
        userServiceImpl.findByUser(user);
       cartServiceImpl.addCart(cart);
        return "redirect:/customLoginPage";
    }

    @GetMapping("/admin")
    public String productPageAdmin(ModelMap modelmap) {
        List<Product> productList = productServiceImpl.productList();
        modelmap.addAttribute("products", productList);
        return "admin";
    }
}
