package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.workshop.awscognitoidp.models.practice.Grade;
import com.workshop.awscognitoidp.models.practice.StudentDetails;
import com.workshop.awscognitoidp.models.practice.Subject;
import com.workshop.awscognitoidp.repositories.practice.SubjectRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
  
  // Repositories
  private final SubjectRepository subjectRepository;
  // Services
  private final StudentServiceDetails studentService;
  private final GradeService gradeService;
  
  @Override
  public Optional<Subject> getSubjectById(Long idSubject) {
    return subjectRepository.findById(idSubject);
  }

  public void assignSubjectStudentGrade(
    Long studentId, 
    Long gradeId, 
    Long subjectId) {
      Optional<StudentDetails> studentDetails = studentService.getStudentDetailsById(studentId);
      Optional<Grade> grade = gradeService.getGradeById(gradeId);
      Optional<Subject> subject = subjectRepository.findById(subjectId);

      // TODO Assing notes 
      // Explanation:
      // A student will be assign a grade for a subject in specific


  }
  
}
