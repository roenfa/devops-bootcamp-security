package com.workshop.awscognitoidp.services;

import com.auth0.jwt.JWT;

import org.springframework.stereotype.Component;

@Component
public class JWTDecoder {

    public String getClaim(String token, String claimName){
        var claim = JWT.decode(token).getClaim(claimName);
        return claim.asString();
    }

    public String getUsername(String token){
        return getClaim(token, "cognito:username");
    }

    public String getEmail(String token){
        return getClaim(token, "email");
    }

    public String getRole(String token){
        return getClaim(token, "custom:role"); 
    }
}
