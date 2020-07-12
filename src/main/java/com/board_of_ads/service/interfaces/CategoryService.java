package com.board_of_ads.service.interfaces;


import com.board_of_ads.models.Category;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(long id);

    List<Category> getAllCategories();

    List<Category> getRootCategories();

    Category findCategoryByNameRu(String nameRu);

    Category findCategoryByNameRuAndParentCategoryIsNotNull(String nameRu);


}
