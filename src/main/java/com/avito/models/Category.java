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


    public Category(String nameRu, String nameEn, Category parentCategory, Set<Posting> postingsInCategory) {
        this.nameRu = nameRu;
        this.parentCategory = parentCategory;
        this.nameEn = nameEn;
        this.postingsInCategory = postingsInCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String nameRu;

    @Column
    private String nameEn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parentCategory")
    private Category parentCategory;

    @OneToMany(cascade = {CascadeType.REFRESH})

    private Set<Posting> postingsInCategory;

    public String getNameRU() {
        return nameRu;
    }

    public void setNameRU(String nameRU) {
        this.nameRu = nameRU;
    }

    public String getNameEN() {
        return nameEn;
    }

    public void setNameEN(String nameEN) {
        this.nameEn = nameEN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
