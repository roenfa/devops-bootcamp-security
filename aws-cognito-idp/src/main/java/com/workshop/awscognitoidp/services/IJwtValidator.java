package com.workshop.awscognitoidp.services;

public interface IJwtValidator {
    public boolean validateJwtToken(String authToken);
}
