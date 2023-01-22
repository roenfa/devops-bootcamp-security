package com.workshop.awscognitoidp.crud.models;


import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student extends AbstractEntity {

    @OneToOne
    private UserDB user;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<StudentGrade> studentGrades;

    @OneToOne
    private Bootcamp bootcamp;
}
