package com.workshop.awscognitoidp.crud.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students_grades")
@JsonIgnoreProperties(value= {"suppliers"})
public class StudentGrade extends AbstractEntity {

    @Column
    private String grade;

    @ManyToOne
    private Subject subject;

    public StudentGrade(Subject subject, String grade){
        this.grade = grade;
        this.subject = subject;
    }
}
