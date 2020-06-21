package com.avito.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "posting")
public class Posting {

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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "image_path")
    private String imagePath;

    public Posting() {
    }

    public Posting(String title, String shortDescription, String fullDescription, Long userId, Long categoryId, String imagePath) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.userId = userId;
        this.categoryId = categoryId;
        this.imagePath = imagePath;
    }

}
