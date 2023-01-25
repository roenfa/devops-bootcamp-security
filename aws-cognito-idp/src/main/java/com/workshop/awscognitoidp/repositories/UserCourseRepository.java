package com.workshop.awscognitoidp.repositories;

import com.workshop.awscognitoidp.models.practice.Course_Users;
import com.workshop.awscognitoidp.models.practice.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<Course_Users, String> {

    @Query(value = "SELECT * FROM course_users cu WHERE cu.student = ?1", nativeQuery = true)
    List<Course_Users> getAllCoursesByStudent(String username);

    @Query(value = "SELECT *  FROM course_users cu WHERE cu.name_course = ?1", nativeQuery = true)
    List<Course_Users> getAllStudentsByCourse(String course);
}
