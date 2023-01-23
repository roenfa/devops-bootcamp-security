package com.workshop.awscognitoidp.services;

import java.util.Optional;

import com.workshop.awscognitoidp.models.Subject;

public interface ISubjectService {
  Subject saveSubject(Subject subject);
  Optional<Subject> getSubjectById(Long idSubject);
  Iterable<Subject> findAll();
}
