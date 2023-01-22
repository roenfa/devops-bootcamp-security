package com.workshop.awscognitoidp.crud.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students_grades")
@JsonIgnoreProperties(value= {"suppliers"})
public class StudentGrade extends AbstractEntity {

    private Long id = super.getId();

    @Column
    private String grade;

    @OneToOne
    private Subject subject;

    public StudentGrade(String grade, Subject subject) {
        this.grade = grade;
        this.subject = subject;
    }

}
