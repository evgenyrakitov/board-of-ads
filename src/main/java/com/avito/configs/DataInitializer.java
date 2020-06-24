package com.avito.configs;

import com.avito.models.Category;
import com.avito.models.Posting;
import com.avito.models.Role;
import com.avito.models.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

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

    @EventListener
    public void handleContextStart(ContextStartedEvent event) {

        Role adminRole = new Role("ADMIN");
        entityManager.persist(adminRole);
        Role userRole = new Role("USER");
        entityManager.persist(userRole);

        Set<Role> forAdmin = new HashSet<>();
        forAdmin.add(adminRole);
        forAdmin.add(userRole);
        // For test, It must be delete in production code
        entityManager.persist(new User("admin@gmail.com", "Test Admin name", "admin", "admin", forAdmin));

        Set<Role> forUser = new HashSet<>();
        forUser.add(userRole);
        User user = new User("test.email.1@gmail.com", "test 1 public name", "qwerty1", "qwerty1", forUser);
        // For test, It must be delete in production code
        entityManager.persist(user);

        entityManager.persist(new Category("Недвижимость", Collections.emptySet()));
        entityManager.persist(new Category("Работа", Collections.emptySet()));
        entityManager.persist(new Category("Транспорт", Collections.emptySet()));
        entityManager.persist(new Category("Услуги", Collections.emptySet()));
        entityManager.persist(new Category("Личные вещи", Collections.emptySet()));
        entityManager.persist(new Category("Для дома и дачи", Collections.emptySet()));
        Category hobby = new Category("Хобби и отдых", Collections.emptySet());
        entityManager.persist(hobby);
        entityManager.persist(new Category("Животные", Collections.emptySet()));
        generateStubPostings(user, hobby);

    }

    // For test, It must be delete in production code
    private void generateStubPostings(User user, Category category) {
        for (int i = 0; i < 100; i++) {
            entityManager.persist(new Posting("Объявление " + (i + 1), category, user, "Полное описание " + (i + 1),
                    "Краткое описание " + (i + 1)));
        }
    }

}
