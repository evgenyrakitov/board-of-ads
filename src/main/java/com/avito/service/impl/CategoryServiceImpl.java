package com.avito.service.impl;

import com.avito.models.Category;
import com.avito.repository.CategoryRepository;
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

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getRootCategories() {
        return categoryRepository.getRootCategories();
    }

}
