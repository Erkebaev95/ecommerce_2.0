package com.erkebaev.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "optionId")
    private List<Value> value;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    public Option() {
    }

    public Option(String name, Category categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return  "\n" + id + " - " + name + " - " + value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }
}
