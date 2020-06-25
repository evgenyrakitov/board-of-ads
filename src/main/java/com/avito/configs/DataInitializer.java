package com.avito.configs;

import com.avito.models.Category;
import com.avito.models.Role;
import com.avito.models.User;
import com.avito.service.interfaces.CategoryService;
import com.avito.service.interfaces.RoleService;
import com.avito.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final RoleService roleService;
    private final UserService userService;
    private final CategoryService categoryService;

//    @PostConstruct
//    private void init() {
//        if (userService.getAllUsers().size() != 0) {
//            return;
//        }
//        Role adminRole = addRole("ADMIN");
//        Role userRole = addRole("USER");
//
//        Set<Role> forAdmin = new HashSet<>();
//        forAdmin.add(adminRole);
//        forAdmin.add(userRole);
//        addUser("admin@gmail.com", "Test Admin name", "admin", "admin", forAdmin);
//
//        Set<Role> forUser = new HashSet<>();
//        forUser.add(userRole);
//        // For test, It must be delete in production code
//        addUser("test.email.1@gmail.com", "test 1 public name", "qwerty1", "qwerty1", forUser);
//
//        addRootCategory("Недвижимость");
//        addRootCategory("Работа");
//        addRootCategory("Транспорт");
//        addRootCategory("Услуги");
//        addRootCategory("Личные вещи");
//        addRootCategory("Для дома и дачи");
//        addRootCategory("Животные");
//        addRootCategory("Хобби и отдых");
//    }

    private Role addRole(String roleName) {
        Role role = new Role(roleName);
        roleService.addRole(role);
        return role;
    }

    private User addUser(String email, String publicName, String password, String confirmPassword, Set<Role> roles) {
        User user = new User(email, publicName, password, confirmPassword, roles);
        userService.addUser(user);
        return user;
    }

    private Category addRootCategory(String name) {
        Category category = new Category(name, Collections.emptySet());
        categoryService.addCategory(category);
        return category;
    }

}
