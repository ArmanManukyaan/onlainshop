package com.example.onlainShop.controller;
import com.example.onlainShop.entity.*;
import com.example.onlainShop.security.CurrentUser;
import com.example.onlainShop.service.impl.CartServiceImpl;
import com.example.onlainShop.service.impl.CategoryServiceImpl;
import com.example.onlainShop.service.impl.OrderServiceImpl;
import com.example.onlainShop.service.impl.ProductServiceImpl;
import com.example.onlainShop.util.ProductUtil;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cardServiceImpl;
    private final ProductServiceImpl productServiceImpl;
    private final OrderServiceImpl orderServiceImpl;
    @GetMapping("/add")
    public String addProductByCart(@AuthenticationPrincipal CurrentUser currentUser,
                                   @RequestParam("productId") int productId) {
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cardServiceImpl.findCartByUserId(user.getId());
        Optional<Product> byId = productServiceImpl.findById(productId);
        if (cartByUserId.isPresent()) {
            Cart cart = cartByUserId.get();
            cart.getProductList().add(byId.get());
            cardServiceImpl.addCart(cart);
        }
        return "redirect:/";
    }
    @GetMapping("/see/context")
    public String order(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cardServiceImpl.findCartByUserId(user.getId());
        Set<Product> productList = cartByUserId.get().getProductList();
        modelMap.addAttribute("totalSum", ProductUtil.countPrice(productList));
        modelMap.addAttribute("userProducts", productList);
        return "orders";
    }
    @GetMapping("do/order")
    public String order(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cardServiceImpl.findCartByUserId(user.getId());
        if (cartByUserId.isPresent()) {
            Cart cart = cartByUserId.get();
            Order order = Order.builder()
                    .user(user)
                    .dateTime(new Date())
                    .productList(new LinkedHashSet<>(cart.getProductList()))
                    .build();
            orderServiceImpl.addOrder(order);
            cardServiceImpl.addCart(cart);
        }
        return "redirect:/";

    }
    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable("productId") int id,
                                 @AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cardServiceImpl.findCartByUserId(user.getId());
        if (cartByUserId.isPresent()){
            Cart cart = cartByUserId.get();
           Set<Product> updatedProducts = ProductUtil.deleteById(cart.getProductList(), id);
           cart.setProductList(updatedProducts);
            cardServiceImpl.addCart(cart);
        }
        return "redirect:/cart/see/context";

    }
}
