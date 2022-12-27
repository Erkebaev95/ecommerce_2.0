package com.erkebaev.shop.model;

import javax.persistence.*;

@Entity
@Table(name = "values")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option optionId;

    public Value() {
    }

    public Value(String name, Product productId, Option optionId) {
        this.name = name;
        this.productId = productId;
        this.optionId = optionId;
    }

    @Override
    public String toString() {
        return  "id: " + id + " - " + name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Option getOptionId() {
        return optionId;
    }

    public void setOptionId(Option optionId) {
        this.optionId = optionId;
    }
}
