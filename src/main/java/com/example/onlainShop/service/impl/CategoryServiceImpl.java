package com.example.onlainShop.service.impl;

import com.example.onlainShop.entity.Category;
import com.example.onlainShop.entity.Product;
import com.example.onlainShop.repasitory.CategoryRepository;
import com.example.onlainShop.repasitory.ProductRepository;
import com.example.onlainShop.service.CategoryService;
import com.example.onlainShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public void addCategory(Category category)  {
        categoryRepository.save(category);
    }

    @Override
    public List<Category> categorytList() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }
}
