package com.company.models;

import com.company.abstractions.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CasinoUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByLogin(username);

        if (!user.isEmpty()) {
            return user.get();
        }

        throw new UsernameNotFoundException("User " + username + " not found!");
    }
}
