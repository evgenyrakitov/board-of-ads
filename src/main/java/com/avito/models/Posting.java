package com.avito.models;

import com.avito.configs.security.AuthProvider;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "posting")
public class Posting {
    private static Logger logger = LoggerFactory.getLogger(Posting.class);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    private Long id;

    private String title;

    private long price;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "full_description")
    private String fullDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "image_path")
    private Set<Images> imagePath;


    //constructors
    public Posting() {
    }

    public Posting(String title, Category category, User user, String fullDescription, String shortDescription, Set<Images> imagePath) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.user = user;
        this.category = category;
        this.imagePath = imagePath;
    }

}
