package com.workshop.awscognitoidp.models;

import com.amazonaws.services.cognitoidp.model.AttributeType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Student {
    private String username;
    private List<AttributeType> attributes;
    private String bootcamp;
    private List<Subject> subjects;


    public Student() {
        Bootcamp bootcamp = Bootcamp.getRandomBootcamp();
        this.bootcamp = bootcamp.name();
        this.subjects = bootcamp.getSubjects();
    }

}
