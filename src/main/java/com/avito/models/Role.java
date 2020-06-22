package com.avito.models;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

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
