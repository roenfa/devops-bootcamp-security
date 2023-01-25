package com.workshop.awscognitoidp.services.practice;

import com.workshop.awscognitoidp.models.practice.Course;
import com.workshop.awscognitoidp.models.practice.Course_Users;
import com.workshop.awscognitoidp.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getAllCoursesByTrainer(String username) {
        return courseRepository.getAllCoursesByTrainer(username);
    }

    public List<Course_Users> getAllStudentsByCourse(String course) {
        return courseRepository.getAllStudentsByCourse(course);
    }


}
