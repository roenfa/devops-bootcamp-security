package com.workshop.springsecurityauth.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.workshop.springsecurityauth.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    // private UserDetails user;
    
    // private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("techgeeknext".equals(username)) {
            return new User("techgeeknext", "$2a$12$ih.C3d5PYDV1lASuq2EXPetzPNCcx6VLcgunsKgPM9iVip0PxIL6C",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        // this.user = userRepository.findUserByEmail(username);

        // if (user == null) {
		// 	throw new UsernameNotFoundException("User not found with username: " + username);
		// }
		// return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
