package com.workshop.awscognitoidp.services;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.workshop.awscognitoidp.crud.models.UserDB;
import com.workshop.awscognitoidp.crud.repositories.UserRepository;

import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService {

    @Autowired
    UserRepository userRepository;

	private UserDB user;

    public User loadByUserName(String username) throws UsernameNotFoundException {
        this.user = userRepository.findByUsername(username);
		String role = user.getRole();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));

        if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		return new User(user.getUsername(), "", authorities);
    }
}
