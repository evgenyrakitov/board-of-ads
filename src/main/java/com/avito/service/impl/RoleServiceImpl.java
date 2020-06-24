package com.avito.service.impl;

import com.avito.configs.security.AuthProvider;
import com.avito.dao.interfaces.RoleDao;
import com.avito.models.Role;
import com.avito.service.interfaces.RoleService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);


    private final RoleDao roleDao;

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }
}
