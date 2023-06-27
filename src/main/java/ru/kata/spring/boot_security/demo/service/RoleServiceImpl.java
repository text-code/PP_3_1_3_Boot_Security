package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dto.RoleDto;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<RoleDto> getAllRoleDto() {
        return listRoleToDto(roleRepository.findAll());
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public RoleDto getRoleDtoById(Long id) {
        return roleToDto(roleRepository.getRoleById(id));
    }


    public List<RoleDto> listRoleToDto(List<Role> roles) {
        List<RoleDto> rolesDto = new ArrayList<>();

        for (Role role : roles) {
            rolesDto.add(roleToDto(role));
        }

        return rolesDto;
    }

    private RoleDto roleToDto(Role role) {
        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setName(role.getName());

        return roleDto;
    }
}
