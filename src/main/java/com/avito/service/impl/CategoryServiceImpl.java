package com.avito.service.impl;

import com.avito.dao.interfaces.CategoryDao;
import com.avito.models.Category;
import com.avito.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);


    private final CategoryDao categoryDao;

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

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }
}
