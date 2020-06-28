package com.avito.configs;

import com.avito.models.Category;
import com.avito.models.Role;
import com.avito.models.User;
import com.avito.models.posting.Posting;
import com.avito.service.interfaces.CategoryService;
import com.avito.service.interfaces.PostingService;
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
    private final PostingService postingService;

    @PostConstruct
    private void init() {
        if (userService.getAllUsers().size() != 0) {
            return;
        }
        Role adminRole = addRole("ADMIN");
        Role userRole = addRole("USER");

        Set<Role> forAdmin = new HashSet<>();
        forAdmin.add(adminRole);
        forAdmin.add(userRole);
        addUser("admin@gmail.com", "Test Admin name", "admin", "admin", forAdmin);

        Set<Role> forUser = new HashSet<>();
        forUser.add(userRole);
        // For test, It must be delete in production code
        addUser("test.email.1@gmail.com", "test 1 public name", "qwerty1", "qwerty1", forUser);

        addRootCategory("Недвижимость");
        addRootCategory("Работа");
        addRootCategory("Транспорт");
        addRootCategory("Услуги");
        addRootCategory("Личные вещи");
        addRootCategory("Для дома и дачи");
        addRootCategory("Животные");
        addRootCategory("Хобби и отдых");

        addPostings();
    }

    private void addPostings() {
        User userUser = new User();
        userUser.setId(2L);

        User adminUser = new User();
        adminUser.setId(1L);

        Category category1 = new Category();
        category1.setId(1L);
        Category category2 = new Category();
        category2.setId(2L);

        Posting posting = new Posting(
                "Коттедж 400 м² на участке 3 сот.",
                category1,
                adminUser,
                "Коттедж на два хозяина. На первом этаже кухня, зал и туалет с душем, второй этаж три комнаты и туалет с ванной, третий этаж-две комнаты. Цокольный этаж с гаражом и комнатой. Готов к заселению, возможна долгосрочная аренда.",
                "Коттедж"
        );
        posting.setPrice(123123L);
        postingService.addPosting(posting);

        posting = new Posting(
                "Posting title",
                category2,
                adminUser,
                "Full description, long text.",
                "Some posting"
        );
        posting.setPrice(100500);
        postingService.addPosting(posting);

        posting = new Posting(
                "Posting title 2",
                category2,
                userUser,
                "Full description, long text. (owner must be user with id 2)",
                "Some posting of second user"
        );
        posting.setPrice(8800);
        postingService.addPosting(posting);
    }

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
