package com.erkebaev.shop.services;

import com.erkebaev.shop.model.Image;
import com.erkebaev.shop.model.Product;
import com.erkebaev.shop.model.User;
import com.erkebaev.shop.repository.ProductRepository;
import com.erkebaev.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Long id) {
         productRepository.deleteById(id);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findUserByUsername(principal.getName());
    }
}
