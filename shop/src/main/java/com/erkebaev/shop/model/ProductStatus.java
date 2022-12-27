package com.erkebaev.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "product_status")
public class ProductStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    /*@OneToMany(mappedBy = "status")
    private List<Order> order;*/

    @OneToMany(mappedBy = "status")
    private List<Product> product;

    public ProductStatus(String name) {
        this.name = name;
    }

    public ProductStatus() {
    }
}
