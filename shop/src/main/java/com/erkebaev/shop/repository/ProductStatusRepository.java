package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {
}