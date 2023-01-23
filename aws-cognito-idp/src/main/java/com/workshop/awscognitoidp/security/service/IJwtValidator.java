package com.workshop.awscognitoidp.security.service;

public interface IJwtValidator {
    public boolean validateJwtToken(String authToken);
}
