package com.erkebaev.shop.services;

import com.erkebaev.shop.model.Role;
import com.erkebaev.shop.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> listRole() {
        return roleRepository.findAll();
    }
}
