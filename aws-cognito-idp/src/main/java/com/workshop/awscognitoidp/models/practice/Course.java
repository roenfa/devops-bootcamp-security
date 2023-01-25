package com.workshop.awscognitoidp.models.practice;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Course {
    @Id
    @Column(name = "nameCourse")
    private String nameCourse;
    private String trainer;



    @JoinColumn(name = "nameBootcamp")
    private String nameBootcamp;

    @ManyToOne
    @JoinColumn(name = "nameBootcamp", insertable = false, updatable = false)
    private Bootcamp bootcamp;

}

