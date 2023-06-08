package com.company.models;

import com.company.abstractions.storage.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CasinoUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.getByLogin(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }

        return user.get();
    }
}
