package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValuesRepository extends JpaRepository<Value, Long> {
}
