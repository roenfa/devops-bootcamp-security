package com.workshop.awscognitoidp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.workshop.awscognitoidp.crud.models.Student;
import com.workshop.awscognitoidp.crud.models.StudentGrade;
import com.workshop.awscognitoidp.crud.services.StudentServiceImp;

@RestController
@RequestMapping(path = "/api/students")
public class StudentsController {
    @Autowired
    private StudentServiceImp studentService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") Long id) {
        Student student = studentService.getById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<StudentGrade>> getGrades(@PathVariable("id") Long id) {
        Student student = studentService.getById(id);
        List<StudentGrade> subjects = student.getStudentGrades();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student o) {
        Student student = studentService.insert(o);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("DAOUser", "/api/students/" + student.getId());
        return new ResponseEntity<>(student, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") Long id, @RequestBody Student p){
        Student student = studentService.update(id, p);
        var httpHeaders = new HttpHeaders();
        httpHeaders.add("DAOUser", "/api/students/" + student.getId());
        return new ResponseEntity<>(student, httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> delete(@PathVariable("id") Long id) {
        Student student = studentService.delete(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
