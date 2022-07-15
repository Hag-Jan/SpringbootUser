package com.example.springuserlabben30march.controller;


import com.example.springuserlabben30march.model.User;
import com.example.springuserlabben30march.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = service.findAll();
        model.addAttribute("usersList", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String showAddUser(Model model) {
        model.addAttribute("user", new User());
        //model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    //PostMapping spara user. add or change somthing.
    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message", "The users has been saved Succesfully!");
        return "redirect:/users";
    }

//    @GetMapping("/users")
//    public String findAll(Model model, String keyword) {
//        if (keyword != null)
//            model.addAttribute("userList", service.findByKeyword(keyword));
//        else {
//            List<User> users = service.findAll();
//            model.addAttribute("userList", users);
//        }
//        return "users";
//    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        User user = service.get(id);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Edit User");
        return "user_form";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUse(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.deleteById(id);
        } catch (UserPrincipalNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/users";
    }
}
