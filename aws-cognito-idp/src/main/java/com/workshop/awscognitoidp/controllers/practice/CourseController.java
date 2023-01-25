package com.workshop.awscognitoidp.controllers.practice;

import com.workshop.awscognitoidp.models.practice.Course;
import com.workshop.awscognitoidp.models.practice.Course_Users;
import com.workshop.awscognitoidp.models.practice.User;
import com.workshop.awscognitoidp.services.practice.CourseService;
import com.workshop.awscognitoidp.services.practice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB')")
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @GetMapping(path = "/all")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') ")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }


    @PostMapping(path = "/trainer")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') or hasAuthority('TRAINER_AGALVISB')")
    public List<Course> getAllCoursesByTrainer(@RequestBody String username) {
        return courseService.getAllCoursesByTrainer(username);
    }

    @PostMapping(path = "/students")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') or hasAuthority('TRAINER_AGALVISB')")
    public List<Course_Users> getAllStudentsByCourse(@RequestBody String course) {
        return courseService.getAllStudentsByCourse(course);
    }



}
