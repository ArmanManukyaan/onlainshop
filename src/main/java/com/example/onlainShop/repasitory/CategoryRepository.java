package com.example.onlainShop.repasitory;
import com.example.onlainShop.entity.Category;
import com.example.onlainShop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
