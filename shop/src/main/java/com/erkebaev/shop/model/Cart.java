package com.erkebaev.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "price")
    private Integer price;

    @Column(name = "total_price")
    private Integer totalPrice;

    public Cart(Integer quantity, Product product, User user, Integer price, Integer totalPrice) {
        this.quantity = quantity;
        this.product = product;
        this.user = user;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }
}
