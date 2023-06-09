package com.example.onlainShop.service;

import com.example.onlainShop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(Product product, MultipartFile multipartFile) throws IOException;
    List<Product> productList();
    Optional<Product> findById(int id);

    void deleteProductById(int id);
}
