package com.erkebaev.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToOne
    @JoinColumn(name = "status_id")
    private List<ProductStatus> status;*/

    private String name;
    private String surname;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String zipCode;
    private String phone;
    private String email;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToMany
    @JoinTable(name = "order_count",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @OneToMany(mappedBy = "order")
    private List<OrderCount> orderCounts;

    public Order(String name, String surname, String city, String street, String house, String apartment,
                 String zipCode, String phone, String email, String comment, User userId) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.comment = comment;
        this.userId = userId;
    }

    public Order(User userId) {
        this.userId = userId;
    }

    public Order() {
    }
}
