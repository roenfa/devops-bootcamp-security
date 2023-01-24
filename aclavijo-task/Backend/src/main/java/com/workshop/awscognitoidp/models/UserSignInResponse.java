package com.workshop.awscognitoidp.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignInResponse {
    private String accessToken;
    private String idToken;
    private String refreshToken;
    private String tokenType;
    private Integer expiresIn;
    private String rol;
    private String username;
    private String email;
}