package com.avito.dao.impl;

import com.avito.dao.interfaces.PostingDao;
import com.avito.models.User;
import com.avito.models.posting.Posting;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PostingDaoImpl implements PostingDao {

    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);

    private final EntityManager entityManager;

    @Override
    public Posting addPosting(Posting posting) {
        return entityManager.merge(posting);
    }

    @Override
    public Posting getPostingById(Long id) {
        return entityManager.createQuery("from Posting where id = :id", Posting.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Posting> getUserPostings(User user) {
        return entityManager.createQuery("from Posting where user = :user", Posting.class).setParameter("user", user).getResultList();
    }

    @Override
    public List<Posting> getAllPostings() {
        return entityManager.createQuery("from Posting", Posting.class).getResultList();
    }
}
