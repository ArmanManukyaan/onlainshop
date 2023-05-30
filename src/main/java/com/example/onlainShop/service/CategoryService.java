package com.example.onlainShop.service;

import com.example.onlainShop.entity.Category;
import com.example.onlainShop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void addCategory(Category category) throws IOException;
    List<Category>categorytList();
}
