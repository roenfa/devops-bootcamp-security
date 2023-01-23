package com.workshop.awscognitoidp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import com.workshop.awscognitoidp.crud.models.Student;
import com.workshop.awscognitoidp.crud.models.StudentGrade;
import com.workshop.awscognitoidp.crud.services.StudentServiceImp;

@RestController
@RequestMapping(path = "/api/students")
public class StudentsController {
    @Autowired
    private StudentServiceImp studentService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TRAINER')")
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") Long id) {
        Student student = studentService.getById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<Set<StudentGrade>> getGrades(@PathVariable("id") Long id) {
        Student student = studentService.getById(id);
        Set<StudentGrade> subjects = student.getStudentGrades();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Student> save(@RequestBody Student o) {
        Student student = studentService.insert(o);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("DAOUser", "/api/students/" + student.getId());
        return new ResponseEntity<>(student, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_TRAINER')")
    public ResponseEntity<Student> update(@PathVariable("id") Long id, @RequestBody Student p){
        Student student = studentService.update(id, p);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("DAOUser", "/api/students/" + student.getId());
        return new ResponseEntity<>(student, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id) {
        Student student = studentService.delete(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
