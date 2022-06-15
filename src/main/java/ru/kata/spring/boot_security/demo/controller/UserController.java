package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.models.User;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository,BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/admin";
    }

    @PutMapping("updateUser/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("editUser/{id}")
    public String showEditForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userRepository.getUserById(id));
        return "edit";
    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

}
