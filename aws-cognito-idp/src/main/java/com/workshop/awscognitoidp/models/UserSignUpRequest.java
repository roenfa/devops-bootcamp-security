package com.workshop.awscognitoidp.models;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequest {
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
}