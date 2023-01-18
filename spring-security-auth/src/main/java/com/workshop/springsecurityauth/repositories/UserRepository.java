package com.workshop.springsecurityauth.repositories;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User(
                    "example@example.com",
                    "$2a$10$EH1K1zzunisARZpWWMZoP.azOxSYK5FSWslA88cBjZ0184j5nrkRa",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User(
                    "rocio@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT"))
            )
    );
    public void add(UserDetails userDetails) {
        APPLICATION_USERS.add(userDetails);
    }

    public UserDetails findUserByEmail(String email) {
        return APPLICATION_USERS
                .stream()
                .filter(u -> u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No user was found"));
    }
}
