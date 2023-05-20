package ru.kata.spring.boot_security.demo.config;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataInit {
    private final RoleService roleService;
    private final UserService userService;

    public DataInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        roleService.saveRole(new Role("ROLE_USER"));

        User user = new User("admin", "admin", 100, "admin@email.com",
                List.of(new Role("ROLE_ADMIN")));

        userService.saveUser(user);
    }
}
