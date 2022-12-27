package com.erkebaev.shop.services;

import com.erkebaev.shop.model.Cart;
import com.erkebaev.shop.model.Product;
import com.erkebaev.shop.model.User;
import com.erkebaev.shop.repository.CartRepository;
import com.erkebaev.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private final UserService userService;

    public List<Cart> listCart() {
        return cartRepository.findAll();
    }

    public Cart getCartByProduct(Product product) {
        User authUser = userService.getAuthUser();
        return cartRepository.getCartByUserAndProduct(authUser, product);
    }

    public Cart getCartById(Long id){
        return cartRepository.findCartById(id);
    }

    public List<Cart> getCartItemsByUser () {
        User auth = userService.getAuthUser();
        return cartRepository.findAllByUser(auth);
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteOrder(Long id) {
        cartRepository.deleteById(id);
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    public void deleteAll(List<Cart> carts) {
        cartRepository.deleteAll(carts);
    }

    public boolean isProductInCart(Product product) {
        User authUser = userService.getAuthUser();
        return cartRepository.existsByUserAndProduct(authUser, product);
    }
}
