package com.company.models.account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class User implements UserDetails {

    public static final String REGEX_LOGIN = "[a-zA-Z0-9]*";
    public static final String REGEX_PASSWORD = "[a-zA-Z0-9]*";

    @Getter
    private final String login;

    @Setter
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    private final Set<Role> roles;

    public User(String login, String password, String email, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (var role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
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
        return roles.stream().anyMatch(x -> x.getName().equals("Enabled"));
    }
}
