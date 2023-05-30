package com.example.onlainShop.repasitory;
import com.example.onlainShop.entity.Product;
import com.example.onlainShop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
