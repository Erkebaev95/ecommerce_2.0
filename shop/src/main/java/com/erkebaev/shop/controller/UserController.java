package com.erkebaev.shop.controller;

import com.erkebaev.shop.model.Role;
import com.erkebaev.shop.model.User;
import com.erkebaev.shop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        return "shop/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "shop/register";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") User user) {
        Role role = userService.findById(2L);
        userService.createUser(user, role);
        return "redirect:/products";
    }

    // Роль - user
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        return "crud/user-info";
    }

    @PostMapping
    public String edit() {
        return "redirect:/products";
    }
}
