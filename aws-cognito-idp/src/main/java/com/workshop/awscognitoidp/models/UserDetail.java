package com.workshop.awscognitoidp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.workshop.awscognitoidp.helpers.UserState;
import com.workshop.awscognitoidp.models.practice.StudentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserState state;

    @OneToOne(mappedBy = "userDetail")
	private StudentDetails studentDetails;
    
    public UserDetail(
            String username,
            String email,
            String password,
            UserState state) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.state = state;
    }
}