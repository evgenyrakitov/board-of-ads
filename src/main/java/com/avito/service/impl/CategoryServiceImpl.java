package com.avito.service.impl;

import com.avito.configs.security.AuthProvider;
import com.avito.dao.interfaces.CategoryDao;
import com.avito.dao.interfaces.UserDao;
import com.avito.models.Category;
import com.avito.models.User;
import com.avito.service.interfaces.CategoryService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);


    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryDao.addCategory(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryDao.updateCategory(category);
    }

    @Override
    public void deleteCategory(long id) {
        categoryDao.deleteCategory(id);
    }
}
