package com.workshop.awscognitoidp.repositories;

import com.workshop.awscognitoidp.models.practice.Course;
import com.workshop.awscognitoidp.models.practice.Course_Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query(value = "SELECT * FROM course c WHERE c.trainer = ?1", nativeQuery = true)
    List<Course> getAllCoursesByTrainer(String username);

    @Query(value = "SELECT * FROM course_users cu WHERE cu.course = ?1", nativeQuery = true)
    List<Course_Users> getAllStudentsByCourse(String course);

}
