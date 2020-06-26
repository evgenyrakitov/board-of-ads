package com.avito.dao.impl;

import com.avito.dao.interfaces.CategoryDao;
import com.avito.models.Category;
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
public class CategoryDaoImpl implements CategoryDao {
    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);

    private final EntityManager entityManager;

    @Override
    public Category addCategory(Category category) {
        return entityManager.merge(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return entityManager.merge(category);
    }

    @Override
    public void deleteCategory(long id) {
        entityManager.createQuery("delete from Category where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<Category> getAllCategories() {
        return entityManager.createQuery("from Category", Category.class).getResultList();
    }
}
