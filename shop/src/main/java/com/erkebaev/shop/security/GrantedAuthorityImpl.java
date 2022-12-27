package com.erkebaev.shop.security;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

    // Формируем конструктор
    private String name;
    private final boolean isRole;

    public GrantedAuthorityImpl(String name, boolean isRole) {
        this.name = name;
        this.isRole = isRole;
    }

    @Override
    public String getAuthority() {
        // Должен вернуть названия привилегии
        // role -> admin
        // permission -> update_product
        // GrantedAuthority -> admin
        // GrantedAuthority -> update_product
        // ROLE_admin -> роль админа
        // ROLE_, admin -> привилегия

        if (isRole) {
            name = "ROLE_" + name;
        }

        return name;
    }
}
