package com.example.onlainShop.repasitory;
import com.example.onlainShop.entity.Cart;
import com.example.onlainShop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findCartByUserId(int id);

}
