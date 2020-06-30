package com.avito.models.posting;

import com.avito.models.Category;
import com.avito.models.Images;
import com.avito.models.Message;
import com.avito.models.User;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Set;

//kalinin_begin_change
//import java.util.logging.Logger;
//kalinin_end

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "posting")
public class Posting {
    private static final Logger logger = LoggerFactory.getLogger(Posting.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posting_id")
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @OneToOne(targetEntity = Category.class)
    private Category category;

    @NonNull
    @ManyToOne(targetEntity = User.class)
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

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Message> messages;

}
