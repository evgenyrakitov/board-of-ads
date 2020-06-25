package com.avito.dao.impl;

import com.avito.dao.interfaces.UserDao;
import com.avito.models.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    private final EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User addUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try {
            user = entityManager.createQuery("from User where login = :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        }catch (NoResultException e){

        }
        return user;
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
