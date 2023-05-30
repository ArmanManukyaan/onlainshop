package com.example.onlainShop.service.impl;

import com.example.onlainShop.entity.Cart;
import com.example.onlainShop.entity.Product;
import com.example.onlainShop.entity.User;
import com.example.onlainShop.entity.UserType;
import com.example.onlainShop.repasitory.CartRepository;
import com.example.onlainShop.repasitory.UserRepository;
import com.example.onlainShop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    @Override
    public void findByUser(User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isEmpty()) {
            String encodePassword = user.getPassword();
            String encode = passwordEncoder.encode(encodePassword);
            user.setPassword(encode);
            user.setUserType(UserType.USER);
            userRepository.save(user);
        }
    }


}
