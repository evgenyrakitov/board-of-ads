package com.avito.service.interfaces;


import com.avito.models.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(long id);

    List<Category> getAllCategories();

    List<Category> getRootCategories();

    List<Category> getCategoriesByParentCategory(Category category);

    Category getCategoryById(Long id);

}
