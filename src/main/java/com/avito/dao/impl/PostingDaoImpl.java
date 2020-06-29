package com.avito.dao.impl;

import com.avito.dao.interfaces.PostingDao;
import com.avito.dao.interfaces.RoleDao;
import com.avito.models.Role;
import com.avito.models.posting.Posting;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
@RequiredArgsConstructor
public class PostingDaoImpl implements PostingDao {
    private static final Logger logger = LoggerFactory.getLogger(PostingDaoImpl.class);

    private final EntityManager entityManager;

    @Override
    public Posting findPostingById(Long id) {
        Posting posting = null;
        try {
            posting = entityManager.createQuery("from Posting where id = :id", Posting.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch (NoResultException e){

        }
        return posting;
    }
}
