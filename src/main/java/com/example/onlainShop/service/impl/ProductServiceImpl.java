package com.example.onlainShop.service.impl;

import com.example.onlainShop.entity.Product;
import com.example.onlainShop.repasitory.CategoryRepository;
import com.example.onlainShop.repasitory.ProductRepository;
import com.example.onlainShop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Value("${onlianeshop.upload.image.path}")
    private String imageUploadPate;


    @Override
    public void addProduct(Product product, MultipartFile multipartFile) throws IOException {
        String fileName = null;
        if (multipartFile != null && multipartFile.getSize() > 0) {
            fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageUploadPate + fileName);
            multipartFile.transferTo(file);
            product.setImgPath(fileName);
        }
        productRepository.save(product);
    }

    @Override
    public List<Product> productList() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }
}
