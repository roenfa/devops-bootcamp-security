package com.workshop.awscognitoidp.controllers.practice;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.awscognitoidp.models.UserDetail;
import com.workshop.awscognitoidp.models.practice.StudentDetails;
import com.workshop.awscognitoidp.services.practice.StudentServiceDetails;
import com.workshop.awscognitoidp.services.practice.UserDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
  private final StudentServiceDetails studentService;

  private final UserDetailService userDetailService;

  @PostMapping("{id}/user")
  public ResponseEntity saveStudent(@PathVariable("id") Long id, @RequestBody StudentDetails studentDetails){
    Optional<UserDetail> userDetail = userDetailService.getUserDetailById(id);
    studentDetails.setUserDetail(userDetail.get());

    return new ResponseEntity<>(
      studentService.saveServiceDetails(studentDetails),
      HttpStatus.CREATED
    );
  }
}
