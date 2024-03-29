package com.company.storage.models;

import com.company.models.account.Role;
import com.company.models.account.User;
import com.company.storage.models.bonus.StorageUserBonus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "balance")
    private BigDecimal balance;

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

        return new User(
                storageUser.id,
                storageUser.login,
                storageUser.password,
                storageUser.email,
                storageUser.roles,
                storageUser.balance);
    }

    //todo [0-100%]
    public Integer getIncreasedChance(){
        return 0;
    }
}