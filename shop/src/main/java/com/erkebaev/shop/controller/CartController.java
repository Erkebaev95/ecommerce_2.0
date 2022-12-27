package com.erkebaev.shop.controller;

import com.erkebaev.shop.model.*;
import com.erkebaev.shop.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final CartService cartService;
    private final UserService userService;

    // Проверка на авторизованность клиента перед покупкой
   /* @GetMapping("/order")
    public String cart(Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        return "shop/cart";
    }*/

    // Добавляем товар в корзину
    /*@GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long product_id,
                            Authentication authentication,
                            Model model) {
        Product product = productService.findById(product_id);
        User user = userService.findByUsername(authentication.getName());
        int price = product.getPrice();
        Cart cart = null;
        long count = 0;
        
        if () {
            count++;
            cart = new Cart(count, product, user, price);
        }

        cartService.addCart(cart);
        model.addAttribute("product", product);
        return "redirect:/listOrders";
    }*/

   /* @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable(value="id") Long id,
                            Principal principal){

        if(principal==null)
            return "login";

        ShoppingCart shoppingCart = shoppingCartService.getActiveShoppingCartByUsername(principal.getName(),false);
        Item itemToAdd = itemService.getItemById(id);

        if(shoppingCart.isItemInCart(itemToAdd)){
            CartItem cartItem = shoppingCart.getCartItemByItem(itemToAdd);
            int cartItemQuantity = cartItem.getQuantity() + 1;
            cartItem.setQuantity(cartItemQuantity);
            itemService.saveCartItem(cartItem);
        }
        else{
            CartItem newCartItem = new CartItem(itemToAdd,1);
            newCartItem.setShoppingCart(shoppingCart);
            shoppingCart.getCartItems().add(newCartItem);
            itemService.saveCartItem(newCartItem);
            shoppingCartService.saveShoppingCart(shoppingCart);
        }

        return "redirect:/listOrders";
    }*/


    /*@GetMapping("addQuantity/{id}")
    public String addQuantity(@PathVariable(value = "id")Long id) {
        CartItem cartItem = itemService.getCartItem(id);
        int oldQuantity = cartItem.getQuantity();
        cartItem.setQuantity(oldQuantity + 1);
        itemService.saveCartItem(cartItem);
        shoppingCartService.saveShoppingCart(cartItem.getShoppingCart());

        return "redirect:/cart";
    }
*/
    /*@GetMapping("decreaseQuantity/{id}")
    public String decreaseQuantity(@PathVariable(value = "id") Long id,
                                   Principal principal) {
        CartItem cartItem = itemService.getCartItem(id);
        int oldQuantity = cartItem.getQuantity();
        cartItem.setQuantity(oldQuantity - 1);

        if(oldQuantity <= 1){
            ShoppingCart shoppingCart = shoppingCartService.getActiveShoppingCartByUsername(principal.getName(),false);
            shoppingCart.getCartItems().remove(cartItem);
            shoppingCartService.saveShoppingCart(shoppingCart);
        }
        itemService.saveCartItem(cartItem);
        shoppingCartService.saveShoppingCart(cartItem.getShoppingCart());

        return "redirect:/cart";
    }*/

    /*@GetMapping("/listOrders")
    public String cartList(Model model) {
        User authUser = userService.getAuthUser();
        //model.addAttribute("carts", authUser.getCart());
        model.addAttribute("carts", authUser.getCarts());
        return "shop/cart";
    }*/

    // Удаляем заказ
    /*@GetMapping("deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        cartService.deleteOrder(id);
        return "redirect:/listOrders";
    }*/

    // Совершаем покупку
    /*@GetMapping("buy/{id}")
    public String buyOrder(@PathVariable String id) {
        return "redirect:/buy";
    }*/

    /*@GetMapping("/buy")
    public String order(Model model) {
        User authUser = userService.getAuthUser();
        //model.addAttribute("carts", authUser.getCart());
        return "shop/orderPlaced";
    }*/
}
