package com.erkebaev.shop.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "display_name")
    private String displayName;

    @OneToMany(mappedBy = "role")
    private List<User> user;

    @Override
    public String getAuthority() {
        return getServiceName();
    }
}
