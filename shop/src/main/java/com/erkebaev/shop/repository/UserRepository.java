package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    //User findByLogin(String login);

    List<User> findAll();

    User findUserById(Long id);

    User findUserByUsername(String username);

    User findByUsername(String s);

    User findByEmail(String email);

    List<User> findAllByUsernameContainingOrEmailContaining(String username, String email);
}
