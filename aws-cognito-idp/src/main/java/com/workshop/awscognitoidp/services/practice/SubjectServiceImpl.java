package com.workshop.awscognitoidp.services.practice;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.workshop.awscognitoidp.helpers.GradesEnum;
import com.workshop.awscognitoidp.models.practice.StudentDetails;
import com.workshop.awscognitoidp.models.practice.Subject;
import com.workshop.awscognitoidp.models.practice.SubjectStudent;
import com.workshop.awscognitoidp.repositories.practice.SubjectRepository;
import com.workshop.awscognitoidp.repositories.practice.SubjectStudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
  
  // Repositories
  private final SubjectRepository subjectRepository;
  private final SubjectStudentRepository subjectStudentRepository;
  // Services
  private final StudentServiceDetails studentService;
  
  @Override
  public Subject saveSubject(Subject subject) {
    return subjectRepository.save(subject);
  }

  @Override
  public Optional<Subject> getSubjectById(Long idSubject) {
    return subjectRepository.findById(idSubject);
  }

  @Override
  public SubjectStudent assignSubjectStudentGrade(Long studentId, Long subjectId, GradesEnum grade) {
    Optional<StudentDetails> studentDetails = studentService.getStudentDetailsById(studentId);
    Optional<Subject> subject = subjectRepository.findById(subjectId);
    
    // Explanation:
    // A student will be assign a grade for a subject in specific
    SubjectStudent subjectStudent = new SubjectStudent(
      subject.get(), 
      studentDetails.get(), 
      grade
    );

    return subjectStudentRepository.save(subjectStudent);
  }

  @Override
  public Iterable<Subject> findAll() {
    return subjectRepository.findAll();
  }
  
}
