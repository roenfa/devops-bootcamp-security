package com.workshop.awscognitoidp.repositories;

import com.workshop.awscognitoidp.models.practice.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    @Query(value = "SELECT * FROM user u WHERE u.role = ?1", nativeQuery = true)
    List<User> getAllUsersByRole(@Param("role") String role);


    @Query(value = "SELECT * FROM user u WHERE u.id_username = ?1", nativeQuery = true)
    User getUserByUsername(String id);


    @Query(value = "SELECT * FROM user u WHERE u.id_username NOT IN (SELECT cu.student  FROM course_users cu WHERE cu.name_course != ?1) AND u.role = 'STUDENT_AGALVISB'", nativeQuery = true)
    List<User> getAllUsersNotAssignedToCourse(String course);

}
