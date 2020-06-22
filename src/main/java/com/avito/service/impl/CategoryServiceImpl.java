package com.avito.service.impl;

import com.avito.dao.interfaces.CategoryDao;
import com.avito.dao.interfaces.UserDao;
import com.avito.models.Category;
import com.avito.models.User;
import com.avito.service.interfaces.CategoryService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

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
