package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepository extends JpaRepository<Value, Long> {
}
