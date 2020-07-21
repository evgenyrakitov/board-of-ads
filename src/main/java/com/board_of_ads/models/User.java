package com.board_of_ads.models;

import com.board_of_ads.models.kladr.City;
import com.board_of_ads.models.kladr.Region;
import com.board_of_ads.models.posting.Posting;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NonNull
    private String email;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    @Size(min = 5, message = "пароль должен быть более 5 символов!")
    private String password;

    @NonNull
    @Transient
    private String passwordConfirm;

    @NonNull
    private String phone;

    @ManyToOne
    private Region region;

    @ManyToOne
    private City city;

    private LocalDateTime dataRegistration = LocalDateTime.now();

    private Boolean enabled = Boolean.TRUE;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_favorite_postings",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "posting_id", referencedColumnName = "id")
    )
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Set<Posting> favoritePostings;

    @JsonManagedReference(value = "user-postings")
    @OneToMany(mappedBy = "user", cascade = {CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Set<Posting> userPostings;

    @NonNull
    private String userIcons;

    @JsonManagedReference(value = "user-messages")
    @OneToMany(mappedBy = "author",cascade = {CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Set<Message> messages;

    @JsonManagedReference(value = "user-notifications")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Notification> notifications;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return getEmail();
    }
}