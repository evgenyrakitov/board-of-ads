package com.avito.models;

import com.avito.configs.security.AuthProvider;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    private static Logger logger = LoggerFactory.getLogger(Role.class);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    //constructors
    public Role() {}

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    //@Override methods

    @Override
    public String getAuthority() {
        return role;
    }

}
