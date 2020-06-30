package com.avito.models;

import com.avito.models.posting.Posting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "categories")
public class Category {
    private static final Logger logger = LoggerFactory.getLogger(Category.class);

    public Category() {

    }


    public Category(String name, Category parentCategory, Set<Category> subCategories) {
        this.name = name;
        this.parentCategory = parentCategory;
        this.subCategories = subCategories;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parentCategory")
    private Category parentCategory;


    @OneToMany(cascade = CascadeType.ALL)
    @Column
    private Set<Category> subCategories;

    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Posting> postingsInCategory;

    public static Logger getLogger() {
        return logger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<Category> subCategories) {
        this.subCategories = subCategories;
    }
}
