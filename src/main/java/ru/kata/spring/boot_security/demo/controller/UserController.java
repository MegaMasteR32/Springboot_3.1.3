package ru.kata.spring.boot_security.demo.controller;


import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.UserRequest;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;


@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;


    @GetMapping(value = "/admin")
    public String index(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());

        return "users";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new UserRequest());

        return "new";
    }

    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute("user") UserRequest userRequest) {
        userRequest.setRoles(List.of(1l,2l));
        userService.saveUsers(userRequest);
        return "redirect:/admin";
    }

    @GetMapping("/admin/update/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PatchMapping("/admin/update/{id}")
    public String updateUser(@ModelAttribute("user") UserRequest userRequest, @PathVariable("id") Long id) {
        userService.updateUser(userRequest);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
    @GetMapping("/user")
    public String userInfo(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }
}