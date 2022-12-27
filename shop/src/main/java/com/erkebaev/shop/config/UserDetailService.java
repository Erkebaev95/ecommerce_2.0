package com.erkebaev.shop.config;

import com.erkebaev.shop.model.User;
import com.erkebaev.shop.repository.UserRepository;
import com.erkebaev.shop.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s)
            throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(s);

        if (user == null)
            throw new UsernameNotFoundException("User not found!");

        return new UserDetailsImpl(user);
    }
}
