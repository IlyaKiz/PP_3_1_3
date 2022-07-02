package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> listUsers();

    User getUserById(long id);

    void deleteUserById(long id);

    User findByUsername(String username);

}
