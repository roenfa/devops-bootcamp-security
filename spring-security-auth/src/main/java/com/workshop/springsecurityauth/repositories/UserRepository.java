package com.workshop.springsecurityauth.repositories;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private static List<UserDetails> applicationUsers =  new ArrayList<>();

    public UserDetails findUserByEmail(String email) {
        return applicationUsers
                .stream()
                .filter(u -> u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No user was found"));
    }

    public void addUser(UserDetails userDetails) {
        applicationUsers.add(userDetails);
    }

    public void removeUser(String email) {
        applicationUsers.removeIf(userDetails -> userDetails.getUsername().equals(email));
    }

}
