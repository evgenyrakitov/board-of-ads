package com.avito.models.posting;

import com.avito.models.Category;
import com.avito.models.Images;
import com.avito.models.Message;
import com.avito.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Category category;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private User user;

    @NonNull
    @Column(name = "full_description")
    private String fullDescription;

    @NonNull
    @Column(name = "short_description")
    private String shortDescription;

    private long price;

    private String cityId;
    private String regionId;

    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "image_path")
    private Set<Images> imagePath;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Message> messages;

}
