package com.pvkstkv.url_availability_notifier.user;

import com.pvkstkv.url_availability_notifier.authorization.ERole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "login"),
                @UniqueConstraint(columnNames = "name")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    private Set<ERole> roles = new HashSet<>();

    public User() {
    }

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
