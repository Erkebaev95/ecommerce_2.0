package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {
}
