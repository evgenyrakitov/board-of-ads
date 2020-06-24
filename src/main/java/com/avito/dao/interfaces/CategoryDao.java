package com.avito.dao.interfaces;

import com.avito.configs.security.AuthProvider;
import com.avito.models.Category;
import com.avito.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface CategoryDao {
    Category addCategory (Category category);
    Category updateCategory (Category category);
    void deleteCategory (long id);
}
