package com.board_of_ads.service.impl;

import com.board_of_ads.models.Role;
import com.board_of_ads.repository.RoleRepository;
import com.board_of_ads.service.interfaces.RoleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
