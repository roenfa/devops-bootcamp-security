package com.workshop.springsecurityauth.services;

import com.workshop.springsecurityauth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsManager {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findUserByEmail(username);
    }

    @Override
    public void createUser(UserDetails user) {
        this.repository.addUser(
                new User(
                        user.getUsername(),
                        this.passwordEncoder.encode(user.getPassword()),
                        user.getAuthorities())
        );
    }

    @Override
    public void updateUser(UserDetails user) {
        // TODO: update user method
    }

    @Override
    public void deleteUser(String username) {
        // TODO: delete user method
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        // TODO: change user password method
    }

    @Override
    public boolean userExists(String username) {
        return Objects.nonNull(this.repository.findUserByEmail(username));
    }
}
