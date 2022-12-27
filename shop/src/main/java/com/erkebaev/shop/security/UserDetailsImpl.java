package com.erkebaev.shop.security;

import com.erkebaev.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {
    private final User user;

    @Autowired
    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*GrantedAuthorityImpl grantedAuthority =
                new GrantedAuthorityImpl("admin", true);

        ArrayList<GrantedAuthorityImpl> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);*/

        return Collections.singletonList(
                new SimpleGrantedAuthority(user.getRole().getServiceName())); // будем получать роли
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Нужно, чтобы получать данные аутентифицированного пользователя
    public User getUser() {
        return this.user;
    }
}
