package com.avito.models;

import lombok.*;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;
import java.util.logging.Logger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "posting")
public class Posting {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(Posting.class);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    private Category category;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


    @NonNull
    @Column(name = "full_description")
    private String fullDescription;

    @NonNull
    @Column(name = "short_description")
    private String shortDescription;

    private long price;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "image_path")
    private Set<Images> imagePath;

}
