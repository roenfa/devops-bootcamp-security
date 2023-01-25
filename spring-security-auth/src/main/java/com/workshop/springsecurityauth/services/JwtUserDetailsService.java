package com.workshop.springsecurityauth.services;

import com.workshop.springsecurityauth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findUserByEmail(username) != null) {
            return new User(username, "$2a$10$ilEHXqIo9MDkwlhSUFw.O.Lmy/c3YAx0lnMHA6lnBx4B4LD7OBecm", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserDetailsService getUserDetailsService() {
        return this;
    }
}
