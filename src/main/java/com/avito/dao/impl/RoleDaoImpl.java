package com.avito.dao.impl;

import com.avito.dao.interfaces.RoleDao;
import com.avito.models.Role;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
@RequiredArgsConstructor
public class RoleDaoImpl implements RoleDao {
    private static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    private final EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findRoleByName(String name) {
        try {
            return entityManager.createQuery("from Role where role = : name", Role.class)
                    .setParameter("role", name)
                    .getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }
}
