package com.workshop.awscognitoidp.services;

import com.auth0.jwt.JWT;
import org.springframework.stereotype.Component;

@Component
public class JwtData {

    public String getTokenData(String token, String key) { 
        var value = JWT.decode(token).getClaim(key);
        return value.asString();
    }

    public String getTokenRole(String token){ 
        return getTokenData(token, "custom:role");  
    }

    public String getEmailToken(String token){ 
        return getTokenData(token, "email");  
    }

    public String getSubjectToken(String token){  
        return getTokenData(token, "subject");  
    }    
}