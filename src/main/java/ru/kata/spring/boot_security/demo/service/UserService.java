package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    User getUserById(Long id);

    User getUserByName(String name);

    Collection<User> getAllUsers();

    void updateUser(User updateUser);

    void deleteUser(Long id);
}
