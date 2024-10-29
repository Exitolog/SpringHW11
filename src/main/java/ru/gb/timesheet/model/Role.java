package ru.gb.timesheet.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    RoleName roleName;

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public Role() {
    }

    @ManyToMany(mappedBy = "roles")
    List<User> users = new ArrayList<>();

    public String getRoleName() {
        return roleName.getName();
    }

    public Long getId() {
        return id;
    }

}
