package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.Cart;
import com.erkebaev.shop.model.Order;
import com.erkebaev.shop.model.Product;
import com.erkebaev.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //Order getOrderByProductsAndUser(User user, Product product);
}
