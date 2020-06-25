package com.avito.dao.impl;

import com.avito.models.Role;
import com.avito.dao.interfaces.RoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

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
