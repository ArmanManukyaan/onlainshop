

package com.example.onlainShop.controller;

import com.example.onlainShop.entity.Product;
import com.example.onlainShop.entity.User;
import com.example.onlainShop.entity.UserType;
import com.example.onlainShop.repasitory.ProductRepository;
import com.example.onlainShop.security.CurrentUser;
import com.example.onlainShop.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ProductServiceImpl productServiceImpl;
    @Value("${onlianeshop.upload.image.path}")
    private String imageUploadPate;

    @GetMapping("/")
    public String productPage(ModelMap modelMap) {
        List<Product> productList = productServiceImpl.productList();
        modelMap.addAttribute("products", productList);
        return "index";
    }
    @GetMapping("/customSuccessLogin")
    public String customSuccessLogin(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getUserType() == UserType.ADMIN) {
                return "redirect:/user/admin";
            } else if (user.getUserType() == UserType.USER) {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/customLogin")
    public String customLogin() {
        return "customLoginPage";
    }

    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("imagPath") String imgPath) throws IOException {
        File file = new File(imageUploadPate + imgPath);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }
}