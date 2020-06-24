package com.avito.configs;

import com.avito.models.Category;
import com.avito.models.Posting;
import com.avito.models.Role;
import com.avito.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Transactional
public class DataInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;

    /*
     * If the code looks strange for you,
     * then follow the link https://stackoverflow.com/questions/17346679/transactional-on-postconstruct-method/18790494#18790494
     * I don't know how not to use transaction here.
     * */
    @PostConstruct
    private void init() {
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                Role adminRole = addRole("ADMIN");
                Role userRole = addRole("USER");

                Set<Role> forAdmin = new HashSet<>();
                forAdmin.add(adminRole);
                forAdmin.add(userRole);
                addUser("admin@gmail.com", "Test Admin name", "admin", "admin", forAdmin);

                // For test, It must be delete in production code
                Set<Role> forUser = new HashSet<>();
                forUser.add(userRole);
                User user = addUser("test.email.1@gmail.com", "test 1 public name", "qwerty1", "qwerty1", forUser);

                addRootCategory("Недвижимость");
                addRootCategory("Работа");
                addRootCategory("Транспорт");
                addRootCategory("Услуги");
                addRootCategory("Личные вещи");
                addRootCategory("Для дома и дачи");
                addRootCategory("Животные");
                Category hobby = addRootCategory("Хобби и отдых");
                generateStubPostings(user, hobby);
            }
        });
    }

    private Role addRole(String roleName) {
        Role role = new Role(roleName);
        entityManager.persist(role);
        return role;
    }

    private User addUser(String email, String publicName, String password, String confirmPassword, Set<Role> roles) {
        User user = new User(email, publicName, password, confirmPassword, roles);
        entityManager.persist(user);
        return user;
    }

    private Category addRootCategory(String name) {
        Category category = new Category(name, Collections.emptySet());
        entityManager.persist(category);
        return category;
    }

    private Posting addPosting(String title, Category category, User user, String fullDescription, String shortDescription) {
        final Posting posting = new Posting(title, category, user, fullDescription, shortDescription);
        entityManager.persist(posting);
        return posting;
    }

    // For test, It must be delete in production code
    private void generateStubPostings(User user, Category category) {
        for (int i = 0; i < 100; i++) {
            addPosting("Объявление " + (i + 1), category, user, "Полное описание " + (i + 1),
                    "Краткое описание " + (i + 1));
        }
    }

}
