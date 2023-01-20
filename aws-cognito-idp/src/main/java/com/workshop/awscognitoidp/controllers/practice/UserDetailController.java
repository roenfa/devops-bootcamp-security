package com.workshop.awscognitoidp.controllers.practice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.awscognitoidp.services.practice.UserDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserDetailController {

  private final UserDetailService userDetailService;

  @GetMapping
  public ResponseEntity findAll() {
    return new ResponseEntity<>(
      userDetailService.findAll(),
      HttpStatus.OK
    );
  }
}
