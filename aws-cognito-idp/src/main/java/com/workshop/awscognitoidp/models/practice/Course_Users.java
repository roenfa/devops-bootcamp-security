package com.workshop.awscognitoidp.models.practice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Course_Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String note;
    private String student;


    @JoinColumn(name = "nameCourse")
    private String nameCourse;

    @ManyToOne
    @JoinColumn(name = "nameCourse", insertable = false, updatable = false)
    private Course course;


}
