package com.erkebaev.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "categoryId")
    private List<Option> option;

    @OneToMany(mappedBy = "categoryId")
    private List<Product> product;
}
