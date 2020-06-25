package com.avito.dao.impl;

import com.avito.configs.security.AuthProvider;
import com.avito.models.User;
import com.avito.dao.interfaces.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.management.InstanceNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User addUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User findUserByLogin(String login) {
        try {
            return entityManager.createQuery("from User where login = :login", User.class)
                    .setParameter("login", login)
                    .getResultList().get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.createQuery("delete from User where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
