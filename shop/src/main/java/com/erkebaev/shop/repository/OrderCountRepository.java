package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.OrderCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCountRepository extends JpaRepository<OrderCount, Long> {
}
