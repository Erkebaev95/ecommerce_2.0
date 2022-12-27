package com.erkebaev.shop.controller;

import com.erkebaev.shop.model.*;
import com.erkebaev.shop.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShoppingCartController {
    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final ProductStatusService statusService;
    private final OrderCountService orderCountService;


    @GetMapping("/order")
    public String cart(Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        return "shop/cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable(value = "id") Long id,
                            Authentication authentication,
                            Model model) {
        User user = userService.findByUsername(authentication.getName());
        Product product = productService.getProductById(id);

        if (cartService.isProductInCart(product)) {
            Cart oldCart = cartService.getCartByProduct(product);
            int cartQuantity = oldCart.getQuantity() + 1;
            int totalPrice = oldCart.getProduct().getPrice() * cartQuantity;
            oldCart.setTotalPrice(totalPrice);
            oldCart.setQuantity(cartQuantity);
            cartService.saveCart(oldCart);
        } else {
            Cart newCart = new Cart(1, product, user, product.getPrice(), product.getPrice());
            cartService.saveCart(newCart);
        }

        model.addAttribute("product", product);
        return "redirect:/listOrders";
    }

    @GetMapping("/buy")
    public String buyOrder(Model model) {
        User authUser = userService.getAuthUser();
        model.addAttribute("order", new Order(authUser));
        model.addAttribute("carts", authUser.getCarts());
        model.addAttribute("orders", orderService.listOrders());
        return "shop/orderPlaced";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(Order order, Authentication authentication) {
        // Сохраняю товар
        User user = userService.findByUsername(authentication.getName());
        Order newOrder = new Order(order.getName(), order.getSurname(), order.getCity(), order.getStreet(), order.getHouse(),
                order.getApartment(), order.getZipCode(), order.getPhone(), order.getEmail(), order.getComment(), user);
        orderService.save(newOrder);

        // Получаю товар из корзины
        List<Cart> carts = cartService.getCartItemsByUser();

        // Сохраняю товар в OrderCount
        for (Cart cart : carts) {
            OrderCount orderCount = new OrderCount();
            orderCount.setOrder(newOrder);
            orderCount.setProduct(cart.getProduct());
            // сохраняю товар в orderCount
            orderCountService.save(orderCount);
        }
        // Удаляю товар из корзины
        cartService.deleteAll(carts);
        return "redirect:/listOrders";
    }

    @GetMapping("addQuantity/{id}")
    public String addQuantity(@PathVariable(value = "id") Long id) {
        Cart cart = cartService.getCartById(id);
        int oldQuantity = cart.getQuantity() + 1;
        int totalPrice = cart.getProduct().getPrice() * oldQuantity;
        cart.setQuantity(oldQuantity);
        cart.setTotalPrice(totalPrice);
        cartService.saveCart(cart);

        return "redirect:/listOrders";
    }

    @GetMapping("decreaseQuantity/{id}")
    public String decreaseQuantity(@PathVariable(value = "id") Long id) {
        Cart cart = cartService.getCartById(id);
        int oldQuantity = cart.getQuantity() - 1;
        cart.setQuantity(oldQuantity);

        int totalPrice = cart.getProduct().getPrice() * oldQuantity;
        cart.setTotalPrice(totalPrice);

        if (cart.getQuantity() < 1) {
            cartService.deleteCart(cart);
        } else {
            cartService.saveCart(cart);
        }

        return "redirect:/listOrders";
    }

    @GetMapping("/listOrders")
    public String cartList(Model model) {
        User authUser = userService.getAuthUser();
        model.addAttribute("carts", authUser.getCarts());
        return "shop/cart";
    }

    @GetMapping("deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        cartService.deleteOrder(id);
        return "redirect:/listOrders";
    }
}
