package com.avito.models;

import com.avito.configs.security.AuthProvider;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

@Data
@Entity
@Table(name = "images")
public class Images {
    private static final Logger logger = LoggerFactory.getLogger(Images.class);


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
