package com.avito.models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "categories")
public class Category {

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
