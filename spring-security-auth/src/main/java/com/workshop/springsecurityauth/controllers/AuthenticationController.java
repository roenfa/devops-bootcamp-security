package com.workshop.springsecurityauth.controllers;

import com.workshop.springsecurityauth.config.JwtTokenUtil;
import com.workshop.springsecurityauth.models.AuthenticationRequest;
import com.workshop.springsecurityauth.models.JwtResponse;
import com.workshop.springsecurityauth.services.JwtUserDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final UserDetails user = jwtUserDetailsService.loadUserByUsername(request.getEmail());

        if (user != null) {
            final String token = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new JwtResponse(token));
        }

        return ResponseEntity.status(400).body("Some error has occurred");
    }
}
