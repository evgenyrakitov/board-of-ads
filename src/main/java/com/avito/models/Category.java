package com.avito.models;

import com.avito.configs.security.AuthProvider;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    private static final Logger logger = LoggerFactory.getLogger(Category.class);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Category> subCategories;

}
