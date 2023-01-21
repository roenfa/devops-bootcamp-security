package com.workshop.awscognitoidp.repositories;

import com.workshop.awscognitoidp.models.Subject;

import java.util.List;

public interface ISubjects {
    List<Subject> getAll();
    List<Subject> getByStudentId(String studentId);
    void addScore(Subject subject);
}
