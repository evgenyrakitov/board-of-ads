package com.avito.models.posting;

import com.avito.models.Category;
import com.avito.models.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "posting")
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    protected Long id;

    protected String title;

    protected long price;

    @Column(name = "short_description")
    protected String shortDescription;

    @Column(name = "full_description")
    protected String fullDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    protected User user;

    @OneToOne(cascade = CascadeType.ALL)
    protected Category category;

    @Column(name = "image_path")
    protected String imagePath;

    public Posting() {
    }

    public Posting(String title, Category category, User user, String fullDescription, String shortDescription, String imagePath) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.user = user;
        this.category = category;
        this.imagePath = imagePath;
    }

}
