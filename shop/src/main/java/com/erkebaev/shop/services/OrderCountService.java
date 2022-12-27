package com.erkebaev.shop.services;

import com.erkebaev.shop.model.Cart;
import com.erkebaev.shop.model.OrderCount;
import com.erkebaev.shop.model.User;
import com.erkebaev.shop.repository.CartRepository;
import com.erkebaev.shop.repository.OrderCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCountService {
    private final OrderCountRepository countRepository;
    private final CartService cartService;

    public OrderCount findById(Long id) {
        return countRepository.findById(id).orElse(null);
    }

    public void save (OrderCount orderCount) {
        countRepository.save(orderCount);
    }
}
