package com.avito.models;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
import lombok.Data;
>>>>>>> origin/dev

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
<<<<<<< HEAD
@AllArgsConstructor
@NoArgsConstructor
=======
>>>>>>> origin/dev
@Entity
@Table(name = "categorys")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Category> subCategories = new HashSet<>();
<<<<<<< HEAD
=======

    public Category() {
    }

    public Category(long id, String name, Set<Category> subCategories) {
        this.id = id;
        this.name = name;
        this.subCategories = subCategories;
    }

>>>>>>> origin/dev
}
