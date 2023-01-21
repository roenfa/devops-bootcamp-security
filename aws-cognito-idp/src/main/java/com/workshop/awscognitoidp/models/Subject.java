package com.workshop.awscognitoidp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Subject {
    private String studentId;
    private String subjectName;
    private int value;
}
