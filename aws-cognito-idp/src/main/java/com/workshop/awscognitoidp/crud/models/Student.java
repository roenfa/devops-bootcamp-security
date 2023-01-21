package com.workshop.awscognitoidp.crud.models;


import java.util.List;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "id")
    private UserDB user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
	private List<StudentGrade> studentGrades;

    @OneToOne
    @JoinColumn(name = "id")
    private Bootcamp bootcamp;
}
