package com.workshop.awscognitoidp.services;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;

@Component
public class JwtDataRetriever {
    public String getTokenRole(String token){
        var role = JWT.decode(token).getClaim("custom:role");
        return role.asString();
    }
    public String getEmailToken(String token){
        var email = JWT.decode(token).getClaim("email");
        return email.asString();
    }

    public String getSubjetcToken(String token){
        var subject = JWT.decode(token).getSubject();
        return subject;
    }
}
