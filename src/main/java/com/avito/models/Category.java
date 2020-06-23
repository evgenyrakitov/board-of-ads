package com.avito.models;

import com.avito.configs.security.AuthProvider;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category {
    private static Logger logger = LoggerFactory.getLogger(Category.class);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Category> subCategories = new HashSet<>();

    public Category() {
    }

    public Category(String name, Set<Category> subCategories) {
        this.name = name;
        this.subCategories = subCategories;
    }

}
