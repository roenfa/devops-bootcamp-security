    package com.workshop.springsecurityauth.controllers;

import com.workshop.springsecurityauth.config.JwtTokenUtil;
import com.workshop.springsecurityauth.models.AuthenticationRequest;
import com.workshop.springsecurityauth.repositories.UserRepository;
import com.workshop.springsecurityauth.services.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

    @RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final UserDetails user = userRepository.findUserByEmail(request.getEmail());

        if (user != null) {
            return ResponseEntity.ok(jwtTokenUtil.generateToken(user));
        }

        return ResponseEntity.status(400).body("Some error has occurred");
    }
    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody AuthenticationRequest request) {
        this.jwtUserDetailsService.register(
                new User(
                        request.getEmail(),
                        request.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_STUDENT"))
                )
        );
        return ResponseEntity.ok("User is created created");
    }

}
