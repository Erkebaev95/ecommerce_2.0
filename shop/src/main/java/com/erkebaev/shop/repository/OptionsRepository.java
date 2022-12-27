package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionsRepository extends JpaRepository<Option, Long> {
}
