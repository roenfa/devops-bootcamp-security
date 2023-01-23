package com.workshop.awscognitoidp.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignUpRequest {
    private String username;
    private String email;
    private String password;
    private String role;
}