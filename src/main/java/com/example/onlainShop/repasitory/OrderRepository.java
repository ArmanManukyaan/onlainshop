package com.example.onlainShop.repasitory;
import com.example.onlainShop.entity.Category;
import com.example.onlainShop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
