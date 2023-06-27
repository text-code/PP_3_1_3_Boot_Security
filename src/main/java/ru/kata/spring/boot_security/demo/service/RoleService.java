package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);

    List<RoleDto> getAllRoleDto();

    List<Role> getAllRole();

    Role getRoleById(Long id);

    RoleDto getRoleDtoById(Long id);

    List<RoleDto> listRoleToDto(List<Role> roles);
}
