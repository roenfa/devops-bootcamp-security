package com.workshop.awscognitoidp.crud.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDB extends AbstractEntity {
    
    @Column(nullable = false)
	private String username;

    @Column
	private String role;
    private String name;
    private String email;
}
