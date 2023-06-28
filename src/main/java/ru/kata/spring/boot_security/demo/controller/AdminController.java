package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final RoleService roleService;
    private final UserService userService;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUserDtoById(id);
    }

    @PostMapping(value = "/registration", consumes = "application/json")
    public void newUser(@RequestBody UserDto userDto) {
        userService.saveUserDto(userDto);
    }

    @PatchMapping("/edit")
    public void update(@RequestBody UserDto userDto) {
        userService.updateUserDto(userDto);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody UserDto userDto) {
        userService.deleteUser(userDto.getId());
    }

    @GetMapping("/current")
    public UserDto currentUser(Principal principal) {
        return userService.getUserDtoByUsername(principal.getName());
    }

    @GetMapping("/roles")
    public List<RoleDto> getRolesDto() {
        return roleService.getAllRoleDto();
    }
}
