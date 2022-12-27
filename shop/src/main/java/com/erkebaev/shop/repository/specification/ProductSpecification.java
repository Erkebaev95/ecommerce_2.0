package com.erkebaev.shop.repository.specification;

import com.erkebaev.shop.model.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    // Predicate - Объект хранящий в себе перечень условий
    // которые должны быть выполнены

    public static Specification<Product> byPrice(Integer from, Integer to) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            /*if (from != null) {
                // price > from
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), from));
            }*/
            if (to != null) {
                // price < to
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), to));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Product> byAll(Long categoryId, Integer from, Integer to, String text) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (categoryId != null && categoryId != 0) {
                predicates.add(criteriaBuilder.equal(root.get("categoryId").get("id"), categoryId));
            }
            if (from != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), from));
            }
            if (to != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), to));
            }
            if (text != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + text + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
