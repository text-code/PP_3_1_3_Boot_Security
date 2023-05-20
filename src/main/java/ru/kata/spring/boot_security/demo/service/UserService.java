package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;

public interface UserService {

    void saveUser(User user);

    User getUserById(Long id);

    User getUserByUsername(String name);

    Collection<User> getAllUsers();

    void updateUser(User updatedUser);

    void deleteUser(Long id);
}
