package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import com.workshop.awscognitoidp.helpers.GradesEnum;
import com.workshop.awscognitoidp.models.practice.Subject;
import com.workshop.awscognitoidp.models.practice.SubjectStudent;

public interface SubjectService {
  Subject saveSubject(Subject subject);
  Optional<Subject> getSubjectById(Long idSubject);
  SubjectStudent assignSubjectStudentGrade(
    Long studentId,
    Long subjectId,
    GradesEnum grade
  );
  Iterable<Subject> findAll();
}
