package com.avito.dao.impl;

import com.avito.dao.interfaces.RoleDao;
import com.avito.models.Role;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
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
        Role role = null;
        try {
            role = entityManager.createQuery("from Role where name = : setName", Role.class)
                    .setParameter("setName", name)
                    .getSingleResult();
        }catch (NoResultException e){

        }
        return role;
    }
}
