package com.workshop.springsecurityauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.workshop.springsecurityauth.repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = userRepository.findUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return user;
    }

    /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("techgeeknext".equals(username)) {
            // return new User("techgeeknext", "$2a$10$ixlPY3AAd4ty1l6E2IsQ9OFZi2ba9ZQE0bP7RFcGIWNhyFrrT3YUi",
            return new User("techgeeknext", "$2a$10$CVT8.knlhVkF2mEK8r8xju1le/tuTzDGHqTO/.BHEaeSNSC4Bj.Oe",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    } */
}
