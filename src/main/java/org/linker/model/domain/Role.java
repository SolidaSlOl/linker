package org.linker.model.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends NamedEntity {
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){}

    public Role(String roleName) {
        this.setName(roleName);
    }
}
