package com.workshop.awscognitoidp.services.practice;

import com.workshop.awscognitoidp.models.practice.Course_Users;
import com.workshop.awscognitoidp.repositories.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseService {

    @Autowired
    private UserCourseRepository userCourseRepository;

    public Course_Users saveUserCourse(Course_Users course_users) {
        return userCourseRepository.save(course_users);
    }


    public List<Course_Users> getAllCoursesByStudent(String username) {
        return userCourseRepository.getAllCoursesByStudent(username);
    }

    public Course_Users updateUserCourse(Course_Users course_users) {
        return userCourseRepository.save(course_users);
    }

    //getAllUsersByRoleAndCourse
    public List<Course_Users> getAllStudentsByCourse(String course) {
        return userCourseRepository.getAllStudentsByCourse(course);
    }
}
