package com.erkebaev.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "productId")
    private List<Value> value;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    private String name;
    private Integer price;
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "product")
    private List<Cart> cart;

    @OneToMany(mappedBy = "product")
    private List<OrderCount> orderCounts;

    @Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;

        return "/user-photos/" + id + "/" + image;
    }
}
