package com.avito.dao.impl;

import com.avito.configs.security.AuthProvider;
import com.avito.models.Role;
import com.avito.dao.interfaces.RoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String name) {
        return entityManager.createQuery("from Role where role = : name", Role.class)
                .setParameter("role", name)
                .getResultList().get(0);
    }
}
