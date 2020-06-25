package com.avito.models;

import com.avito.configs.security.AuthProvider;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Images {
    private static final Logger logger = LoggerFactory.getLogger(Images.class);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "image_path")
    private String imagePath;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User owner;

    @NonNull
    private String hash;

    @NonNull
    private String metaInfo;

}
