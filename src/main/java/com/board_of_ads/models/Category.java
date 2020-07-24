package com.board_of_ads.models;

import com.board_of_ads.models.posting.Posting;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "categories")
public class Category {
    private static final Logger logger = LoggerFactory.getLogger(Category.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // If this is "false", then category is counted as deleted
    @Column private boolean active = true;

    @Column private String nameRu;

    @Column private String nameEn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parentCategory")
    private Category parentCategory;

    @OneToMany(cascade = {CascadeType.REFRESH})
    private Set<Posting> postingsInCategory;

    public Category() {}

    public Category(
            String nameRu, String nameEn, Category parentCategory, Set<Posting> postingsInCategory) {
        this.nameRu = nameRu;
        this.parentCategory = parentCategory;
        this.nameEn = nameEn;
        this.postingsInCategory = postingsInCategory;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
