package com.avito.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;

    private String hash;

    private String metaInfo;


    //constructors
    public Images() {};

    public Images(String imagePath, User owner, String hash, String metaInfo) {
        this.imagePath = imagePath;
        this.owner = owner;
        this.hash = hash;
        this.metaInfo = metaInfo;
    }
}
