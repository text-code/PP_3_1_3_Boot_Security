package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RegistrationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(
            UserService userService,
            PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void init() {
        createAdmin();
        createUser();
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
    }


    private void createRoles() {
//        roleRepository.save(new Role("ROLE_ADMIN"));
//        roleRepository.save(new Role("ROLE_USER"));
    }

    private void createAdmin() {
        User user = new User("admin", "admin", 100, "admin@email.com", List.of());

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");

        user.setRoles(List.of(roleAdmin));

        register(user);
    }

    private void createUser() {
        User user = new User("user", "user", 100, "user@email.com", List.of());

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");

        user.setRoles(List.of(roleUser));

        register(user);
    }
}
