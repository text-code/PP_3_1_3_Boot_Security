package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void saveUserDto(UserDto userDto);

    UserDto getUserDtoById(Long id);

    UserDto getUserDtoByUsername(String name);

    List<UserDto> getAllUsers();

    void updateUserDto(UserDto userDto);

    void deleteUser(Long id);
}
