package com.erkebaev.shop.services;

import com.erkebaev.shop.model.UserStatus;
import com.erkebaev.shop.repository.UserStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStatusService {
    private final UserStatusRepository userStatusRepository;

    public List<UserStatus> list() {
        return userStatusRepository.findAll();
    }
}
