package com.workshop.awscognitoidp.controllers.practice;


import com.workshop.awscognitoidp.models.practice.Bootcamp;
import com.workshop.awscognitoidp.models.practice.Course_Users;
import com.workshop.awscognitoidp.models.practice.User;
import com.workshop.awscognitoidp.services.practice.BootcampService;
import com.workshop.awscognitoidp.services.practice.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usercourse")
public class UserCourseController {

    @Autowired
    private UserCourseService userCourseService;


    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') or hasAuthority('TRAINER_AGALVISB')")
    public Course_Users createBootcamp(@RequestBody Course_Users course_users) {
        return userCourseService.saveUserCourse(course_users);
    }

        @PostMapping(path = "/courses")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') or hasAuthority('STUDENT_AGALVISB')")
    public List<Course_Users> getAllCoursesByStudent(@RequestBody String username) {
        return userCourseService.getAllCoursesByStudent(username);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') or hasAuthority('TRAINER_AGALVISB')")
    public Course_Users updateBootcamp(@RequestBody Course_Users course_users) {
        return userCourseService.updateUserCourse(course_users);
    }

    @PostMapping(path = "/getStudentsByCourse")
    @PreAuthorize("hasAuthority('ADMIN_AGALVISB') or hasAuthority('TRAINER_AGALVISB')")
    public List<Course_Users> getStudentsByCourse(@RequestBody String course) {
        return userCourseService.getAllStudentsByCourse(course);
    }

}
