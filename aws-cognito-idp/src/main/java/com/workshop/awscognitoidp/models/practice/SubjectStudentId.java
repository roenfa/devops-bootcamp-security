package com.workshop.awscognitoidp.models.practice;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectStudentId implements Serializable {
  @Column(name = "subject_id")
  private Long subjectId;
 
  @Column(name = "student_id")
  private Long studentId;
}
