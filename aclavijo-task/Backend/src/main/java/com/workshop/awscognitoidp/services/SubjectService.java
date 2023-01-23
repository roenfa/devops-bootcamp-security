package com.workshop.awscognitoidp.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.workshop.awscognitoidp.models.Subject;
import com.workshop.awscognitoidp.repositories.SubjectRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectService implements ISubjectService {
  
  // Repositories
  private final SubjectRepository subjectRepository;

  @Override
  public Subject saveSubject(Subject subject) {
    return subjectRepository.save(subject);
  }

  @Override
  public Optional<Subject> getSubjectById(Long idSubject) {
    return subjectRepository.findById(idSubject);
  }

  @Override
  public Iterable<Subject> findAll() {
    return subjectRepository.findAll();
  }
  
}