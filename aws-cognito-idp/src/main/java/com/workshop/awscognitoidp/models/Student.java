package com.workshop.awscognitoidp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class Student {
    private String id;
    private String username;
    private String email;
    private List<Subject> subjects;
}
