package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return listUserToDTO(userRepository.findAll());
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        return userToDto(userRepository.getUserById(id));
    }

    @Override
    public UserDto getUserDtoByUsername(String name) {
        return userToDto(userRepository.getUserByUsername(name));
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveUserDto(UserDto userDto) {
        userRepository.save(dtoToUser(userDto));
    }

    @Override
    @Transactional
    public void updateUserDto(UserDto userDto) {
        userRepository.save(dtoToUser(userDto));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("'%s' not found", username));
        }
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setAge(user.getAge());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRoles(roleService.listRoleToDto(user.getRoles()));

        return userDto;
    }

    private List<UserDto> listUserToDTO(List<User> users) {
        List<UserDto> allUser = new ArrayList<>();
        for (User user : users) {
            allUser.add(userToDto(user));
        }
        return allUser;
    }

    private User dtoToUser(UserDto userDto) {
        User user = new User();
        List<Role> roles = new ArrayList<>();

        if (userDto.getId() != null) user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(
                (userDto.getPassword().equals(""))
                        ? userRepository.getUserById(userDto.getId()).getPassword()
                        : passwordEncoder.encode(userDto.getPassword())
        );


        for (RoleDto roleDto : userDto.getRoles()) {
            roles.add(roleService.getRoleById(roleDto.getId()));
        }
        user.setRoles(roles);

        return user;
    }
}
