package com.board_of_ads.service.impl;

import com.board_of_ads.models.Category;
import com.board_of_ads.repository.CategoryRepository;
import com.board_of_ads.service.interfaces.CategoryService;
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

    @Override
    public Category findCategoryByNameRu(String nameRu) {
        return categoryRepository.findCategoryByNameRu(nameRu);
    }

    @Override
    public Category findCategoryByNameEn(String nameEn) {
        return categoryRepository.findCategoryByNameEn(nameEn);
    }

    @Override
    public Category findCategoryByNameRuAndParentCategoryIsNotNull(String nameRu) {
        return categoryRepository.findCategoryByNameRuAndParentCategoryIsNotNull(nameRu);
    }

    @Override
    public List<Category> findAllByParentCategory(Category parentCategory) {
        return categoryRepository.findAllByParentCategory(parentCategory);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id);
    }


}
