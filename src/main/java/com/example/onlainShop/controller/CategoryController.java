package com.example.onlainShop.controller;
import com.example.onlainShop.entity.Category;
import com.example.onlainShop.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/add")
    public String registerPage(ModelMap modelMap) {
        List<Category> categoryList = categoryServiceImpl.categorytList();
        modelMap.addAttribute("categories", categoryList);
        return "categoryAdd";
    }
    @PostMapping("/add")
    public String register(@ModelAttribute Category category) {
        categoryServiceImpl.addCategory(category);
        return "redirect:/";
    }

}
