package com.company.storage.models;

import com.company.models.account.Role;
import com.company.models.account.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class StorageUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public static User toModel(StorageUser storageUser) {
        if (storageUser == null) {
            return null;
        }

        return new User(storageUser.login, storageUser.password, storageUser.email, storageUser.roles);
    }

    public StorageUser() {

    }
}