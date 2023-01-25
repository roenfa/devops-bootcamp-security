package com.workshop.awscognitoidp.models.practice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id_username")
    private String idUsername;
    private String email;
    private String role;

}
