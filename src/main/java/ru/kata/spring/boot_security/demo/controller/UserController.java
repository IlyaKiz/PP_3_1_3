package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.models.User;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping(value = "/admin")
    public String admin(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/users";
    }

    @GetMapping(value = "/user")
    public String user(Principal principal, Model model) {
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "/user";
    }

    @GetMapping("/new")
    public String showFormForNewUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("editUser/{id}")
    public String showEditForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userRepository.findById(id));
        return "new";
    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

}
