package com.workshop.awscognitoidp.models.practice;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.workshop.awscognitoidp.helpers.GradesEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject_student")
@Data
@NoArgsConstructor
public class SubjectStudent {
  @EmbeddedId
  private SubjectStudentId subjectStudentId;

  @ManyToOne
  @MapsId("subjectId")
  private Subject subject;

  @ManyToOne
  @MapsId("studentId")
  private StudentDetails student;

  @Column
  private GradesEnum grade;

  public SubjectStudent(Subject subject, StudentDetails student, GradesEnum grade){
    this.subject = subject;
    this.student = student;
    this.grade = grade;
  }
}
