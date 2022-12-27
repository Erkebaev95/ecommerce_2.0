package com.erkebaev.shop.repository;

import com.erkebaev.shop.model.Category;
import com.erkebaev.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    //findAllByCategory -> select Product p where p.category = ?1
    //@Query("select p from Product p")
    @Query("select p from Product p where p.categoryId = ?1")
    List<Product> findAllByCategory(Category category);

    List<Product> findProductById(Long id);

    Product removeProductById(Long id);

    Product getProductById(Long id);

    // select p where product p where p.price between ?1 and ?2
    List<Product> findAllByPriceBetween(@Param("min") int from, @Param("max") int to);

    // select p where product p where p.category = ?1 and p.price between ?1 and ?2
    //@Query("select p from Product p where p.categoryId = ?1 and p.price between ?2 and ?3")
    List<Product> findAllByCategoryIdAndPriceBetween(Category category, Integer from, Integer to);

    // min > 0 -> 460.000 > 0 ->
    //@Query("select p from Product p where p.categoryId = ?1 and p.price > 0")
    List<Product> findAllByCategoryIdAndPriceAfter(Category categoryId, Integer price);

    // min < 1m
    //@Query("select p from Product p where p.categoryId = ?1 and p.price < 1000000")
    List<Product> findAllByCategoryIdAndPriceBefore(Category categoryId, Integer price);

    List<Product> findAllByPriceAfter(Integer price);

    List<Product> findAllByPriceBefore(Integer price);

    List<Product> findAllByPriceBetween(Integer price, Integer price2);


    // Query - аннотация детализирующая какой именно должен выполняться запрос при вызове метода.
    @Query("select p from Product p where p.categoryId = ?1 and p.price between ?2 and ?3")

    List<Product> method(Category category, int from, int to);

    // find* - выборка данных из репозитория.
    // findBy - выборка одной записи.
    // findAllBy - выборка нескольких записей.

    // findByName - выборка названия товара которого равно 'name'
    // findAllByName -> select Product p where p.name = ?1
    Product findByName(String name);

    // findByNameStartsWith - выбрать товар название которого начинается на "prefix"
    // findAllByName -> select Product p where p.prefix like '%1%'
    Product findByNameStartingWith(String prefix);

    // findByNameEndsWith - выбрать товар название которого заканчивается на "suffix"
    // findAllByName -> select Product p where p.suffix like '%1%'
    Product findByNameEndingWith(String suffix);

    // findByNameContains - выбрать товар в название которого присутствует фрагмент из 'fragment'
    // findAllByName -> select Product p where like '%1%'
    Product findByNameContains(String fragment);
}
