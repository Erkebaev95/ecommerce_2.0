package com.erkebaev.shop.services;

import java.util.List;

import com.erkebaev.shop.model.Cart;
import com.erkebaev.shop.model.Order;
import com.erkebaev.shop.model.Product;
import com.erkebaev.shop.model.User;
import com.erkebaev.shop.repository.CartRepository;
import com.erkebaev.shop.repository.OrderRepository;
import com.erkebaev.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

}
