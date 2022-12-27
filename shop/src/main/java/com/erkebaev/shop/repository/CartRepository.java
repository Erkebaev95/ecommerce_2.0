package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.Cart;
import com.erkebaev.shop.model.Product;
import com.erkebaev.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    boolean existsByUserAndProduct(User user, Product product);

    List<Cart> findAllByUser(User username);
    List<Cart> findCartByProduct(Product product);
    List<Cart> findAll();
    Cart findCartById(Long id);
    Cart getCartById(Long id);
    Cart getCartByUserAndProduct(User user, Product product);

    Cart removeCartByProduct(Product product);
    Cart removeCartById(Long id);
    Cart removeCartByQuantity(int number);

}
