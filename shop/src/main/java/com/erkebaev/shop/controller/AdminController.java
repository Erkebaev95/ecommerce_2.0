package com.erkebaev.shop.controller;

import com.erkebaev.shop.model.Category;
import com.erkebaev.shop.model.Product;
import com.erkebaev.shop.model.User;
import com.erkebaev.shop.services.*;
import com.erkebaev.shop.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final UserStatusService userStatusService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final OptionService optionService;
    private final RoleService roleService;
    private final ProductStatusService statusService;
    private final ImageService imageService;
    private final OrderService orderService;


    //////////// Работа с Админом /////////////////
    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("users", userService.list());
        return "shop/adminHome";
    }

    /////////////// Работа с пользователями ////////////////////////

    // Список пользователя
    @GetMapping("/admin/listUsers")
    public String listUsers(Model model) {
        model.addAttribute("userStatus", userStatusService.list());
        model.addAttribute("users", userService.list());
        return "shop/users";
    }

    // Редактируем данные
    @GetMapping("admin/editUser/{id}")
    public String userEdit(@PathVariable("id") Long id,
                           Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.listRole());
        return "shop/editUser";
    }

    @PostMapping("/admin/editUser")
    public String userEdit(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    // Удаляем пользователя
    @GetMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    // Блокируем пользователя
    @PostMapping("admin/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin/listUsers";
    }

    //////////////// Работа с продуктами ///////////////

    // Список категории
    @GetMapping("/admin/categories")
    public String listCategory(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "shop/categories";
    }

    // Добавляем категорию
    @GetMapping("/admin/addCategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "shop/categoriesAdd";
    }

    @PostMapping("/admin/saveCategory")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/admin";
    }

    // Редактируем категорию


    // Удаляем категорию
    public String deleteCategory() {

        return "";
    }

    // Список продуктов
    @GetMapping("/admin/listProduct")
    public String listProduct(Model model) {

        model.addAttribute("products", productService.findAll());
        return "shop/products";
    }

    /*@GetMapping("/admin/listProduct")
    public String listProduct(//@RequestParam(value = "id", required = false) Long id,
                              Model model) {
        //Product product = productService.getProductById(id);
        model.addAttribute("products", productService.findAll());
        //model.addAttribute("image", product.getImages());
        return "shop/products";
    }*/

    // Добавляем новый продукт
    @GetMapping("/admin/addProduct")
    public String createProduct(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("status", statusService.statusList());
        model.addAttribute("options", optionService.listOption());
        model.addAttribute("values", optionService.listValue());
        model.addAttribute("product", new Product());
        return "shop/productsAdd";
    }

    // Сохраняем продукт
    @PostMapping("/admin/saveProduct")
    public String saveProduct(@RequestParam("file") MultipartFile file,
                              Product product) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        product.setImage(fileName);
        Product saveProduct = productService.addProduct(product);
        String uploadDir = "user-photos/" + saveProduct.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);

        return "redirect:/admin/listProduct";
    }

    /*@PostMapping("/admin/saveProduct")
    public String saveProduct(@RequestParam("file") MultipartFile file,
                              Principal principal,
                              Product product, Model model) throws IOException {
        productService.addProduct(product);
        //productService.saveProduct(principal, product, file1, file2, file3);
        return "redirect:/admin/listProduct";
    }*/

    // Редактируем данные по id продукта
    @GetMapping("admin/editProduct/{id}")
    public String editProduct(@PathVariable("id") Long id,
                              Model model) {
        Product product = productService.findById(id);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("status", statusService.statusList());
        model.addAttribute("product", product);
        return "shop/editProduct";
    }

    // Сохраняем работу по редактировании
    @PostMapping("/admin/editProduct")
    public String updateProduct(@RequestParam("file") MultipartFile file,
                                Product product) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        product.setImage(fileName);
        Product saveProduct = productService.addProduct(product);
        String uploadDir = "user-photos/" + saveProduct.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return "redirect:/admin";
    }

    /*@PostMapping("/admin/editProduct")
    public String updateProduct(@RequestParam("file") MultipartFile file,
                                Product product) {

        productService.addProduct(product);
        return "redirect:/admin";
    }*/

    // Удаляем продукт
    @GetMapping("admin/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    ///////    Работа с заказами      ////////
    @GetMapping("/admin/listOrders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.listOrders());
        return "shop/listOrders";
    }

    // Удаляем заказа
    @GetMapping("admin/deleteOrder/{id}")
    public String deleteOrders(@PathVariable("id") Long id) {
        orderService.delete(id);
        return "redirect:/admin";
    }
}
