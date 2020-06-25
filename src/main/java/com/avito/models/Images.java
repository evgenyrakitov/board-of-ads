package com.avito.models;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class Images {

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
