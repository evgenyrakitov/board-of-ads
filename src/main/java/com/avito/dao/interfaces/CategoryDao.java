package com.avito.dao.interfaces;

import com.avito.models.Category;
import com.avito.models.User;

import java.util.List;

public interface CategoryDao {
    Category addCategory (Category category);
    Category updateCategory (Category category);
    void deleteCategory (long id);
}
