package com.erkebaev.shop.controller;

import com.erkebaev.shop.model.Category;
import com.erkebaev.shop.model.Product;
import com.erkebaev.shop.repository.ProductRepository;
import com.erkebaev.shop.repository.specification.ProductSpecification;
import com.erkebaev.shop.services.CategoryService;
import com.erkebaev.shop.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CategoryService categoryService;

    // Трети вариант выборки продукта
    // Реализация через JpaSpecification
    @GetMapping("/products")
    public String listProduct(@RequestParam(value = "categoryId", required = false) Long id,
                              @RequestParam(value = "min", required = false) Integer min,
                              @RequestParam(value = "max", required = false) Integer max,
                              @RequestParam(value = "searchByName", required = false) String searchByName,
                              Principal principal, Model model) {
        List<Category> categories = categoryService.findAll();
        //Product product = productService.getProductById(id);
        List<Product> result = productRepository
                .findAll(ProductSpecification.byAll(id, min, max, searchByName));

        //model.addAttribute("image", product.getImages());
        model.addAttribute("result", result);
        model.addAttribute("categories", categories);
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "shop/shop";
    }

    // Второй вариант выборки продукта по категории
    // Реализация через JpaRepository
   /* @GetMapping("/products")
    public String listProduct(@RequestParam(value = "categoryId",
            required = false) Long id, Model model,
                              @RequestParam(value = "min", required = false) Integer min,
                              @RequestParam(value = "max", required = false) Integer max) {
        List<Product> products = productService.findAll();
        List<Category> categories = categoryService.findAll();
        List<Product> result = null;

        // Выбираем продукт по категории и фильтруем цены
        // фильтрация по цене товара от 10 до 100
        if (id == null || id == 0) {
            result = products;

            if (min != null && max == null) {
                result = productRepository.findAllByPriceAfter(min);
            } else if (min == null && max != null) {
                result = productRepository.findAllByPriceBefore(max);
            } else if (min != null && max != null) {
                result = productRepository.findAllByPriceBetween(min, max);
            }

        } else if (min != null && max == null) {
            Optional<Category> category = categoryRepository.findById(id);

            // result = productRepository.findAllByCategoryIdAndPriceBetween(category.orElse(null), min,1000000);
            result = productRepository
                    .findAllByCategoryIdAndPriceAfter(category.orElse(null), min);
        } else if (min == null && max != null) {
            Optional<Category> category = categoryRepository.findById(id);

            //result = productRepository.findAllByCategoryIdAndPriceBetween(category.orElse(null), 0,max);
            result = productRepository
                    .findAllByCategoryIdAndPriceBefore(category.orElse(null), max);
        } else if (min != null && max != null) {
            Optional<Category> category = categoryRepository.findById(id);

            result = productRepository
                    .findAllByCategoryIdAndPriceBetween(category.orElse(null), min, max);
        }

        model.addAttribute("result", result);
        model.addAttribute("categories", categories);
        return "crud/welcome";
    }*/

    // Первый вариант по выборки продукта по категории
    /*@GetMapping("/products")
    public String listProduct(@RequestParam(value = "categoryId",
            required = false) Long id,
                              Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> products = productService.findAll();
        List<Product> result;

        if (id == null || id == 0) {
            result = products;
        } else {
            result = new ArrayList<>();
            for (Product p : products) {
                if (p.getCategoryId().getId().equals(id)) {
                    result.add(p);
                }
            }
        }

        model.addAttribute("result", result);
        model.addAttribute("categories", categories);
        return "crud/welcome";
    }*/
}
