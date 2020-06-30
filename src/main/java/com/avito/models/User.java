package com.avito.models;

import com.avito.models.posting.Posting;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

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
    private String login;   //email is login
    //!notice! check use regexp valid email!

    @NonNull
    private String publicName;   //view from any user name
    //check if word not in blacklist.

    @NonNull
    @Size(min = 5, message = "пароль должен быть более 5 символов!")
    private String password;

    @NonNull
    @Transient
    private String passwordConfirm;

    @NonNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Posting> favoritePostings;

    @OneToMany(mappedBy = "user",
    cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) //по умолчанию FetchType.LAZY
    private Set<Posting> userPostings; //возможность сохранить все посты пользователя вместе с пользователем
    //возможность удалить все посты пользователя, если пользователь будет удален

    private String userIcons;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Message> messages;

    @Override
    public String getPassword() {
        return password;
    }

    //Override methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return login;
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

}