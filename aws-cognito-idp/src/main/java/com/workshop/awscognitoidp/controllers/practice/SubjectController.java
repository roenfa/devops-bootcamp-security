package com.workshop.awscognitoidp.controllers.practice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.awscognitoidp.helpers.GradesEnum;
import com.workshop.awscognitoidp.models.practice.Subject;
import com.workshop.awscognitoidp.models.practice.SubjectStudent;
import com.workshop.awscognitoidp.services.practice.SubjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {
  
  private final SubjectService subjectService;

  @PostMapping
  public ResponseEntity saveSubject(@RequestBody Subject subject){
    return new ResponseEntity<>(
      subjectService.saveSubject(subject),
      HttpStatus.CREATED
    );
  }

  @PutMapping("{subjectId}/grade/{grade}/student/{studentId}")
  public ResponseEntity saveSubjectStudent(
    @PathVariable("subjectId") Long subjectId, 
    @PathVariable("grade") GradesEnum grade,
    @PathVariable("studentId") Long studentId
    ){
      System.out.println("********saveSubjectStudent*******");
      System.out.println(subjectId);
      System.out.println(grade);
      System.out.println(studentId);
      System.out.println("********saveSubjectStudent*******");

      SubjectStudent subjectStudent = subjectService.assignSubjectStudentGrade(
        studentId, 
        subjectId, 
        grade
      );

    return new ResponseEntity<>(
      subjectStudent,
      HttpStatus.CREATED
    );
  }
}
