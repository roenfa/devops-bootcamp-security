package com.workshop.awscognitoidp.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject_student")
@Data
@NoArgsConstructor
public class StudentSubject {
  @EmbeddedId
  private StudentSubjectId StudentSubjectId;

  @ManyToOne
  @MapsId("subjectId")
  private Subject subject;

  @ManyToOne
  @MapsId("studentId")
  private Student student;

  @Column
  private Integer grade;

  public StudentSubject(Subject subject, Student student, Integer grade){
    this.subject = subject;
    this.student = student;
    this.grade = grade;
  }
}