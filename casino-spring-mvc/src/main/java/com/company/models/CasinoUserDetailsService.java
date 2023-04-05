package com.company.models;

import com.company.storage.jpa.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CasinoUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUserLogin(username);
        if (user != null)
            return new CasinoUsersDetails(user);
        throw new UsernameNotFoundException("User " + username + " not found!");
    }
}
