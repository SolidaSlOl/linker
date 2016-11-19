package org.linker.model.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends NamedEntity {
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<User> users;
}
