package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import com.workshop.awscognitoidp.models.practice.Subject;

public interface SubjectService {
  Optional<Subject> getSubjectById(Long idSubject);
  void assignSubjectStudentGrade(
    Long studentId,
    Long gradeId,
    Long subjectId
  );
}
