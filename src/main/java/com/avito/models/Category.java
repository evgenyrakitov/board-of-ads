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

    @OneToMany(cascade = {CascadeType.REFRESH})
    private Set<Posting> postingsInCategory;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parentCategory")
    private Category parentCategory;


    public Category(String name, Category parentCategory, String local, Set<Posting> postingsInCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
        this.local = local;
//        this.postingsInCategory = postingsInCategory;
    }

    @Column(name = "local")
    private String local;

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

    public Set<Posting> getPostingsInCategory() {
        return postingsInCategory;
    }

    public void setPostingsInCategory(Set<Posting> postingsInCategory) {
        this.postingsInCategory = postingsInCategory;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
