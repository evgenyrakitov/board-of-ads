package com.avito.service.interfaces;


import com.avito.models.Category;
import com.avito.models.User;

import java.util.List;

public interface CategoryService {
        Category addCategory (Category category);
        Category updateCategory (Category category);
        void deleteCategory (long id);
}
