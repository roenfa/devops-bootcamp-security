package com.workshop.awscognitoidp.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.awscognitoidp.models.Student;
import com.workshop.awscognitoidp.security.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {


    @Value(value = "${aws.cognito.userPoolId}")
    private String userPoolId;

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public List<Student> retrieveAllStudents() {
        try {
            ListUsersInGroupResult listUsersInGroupResult = cognitoClient.listUsersInGroup(
                    new ListUsersInGroupRequest()
                            .withUserPoolId(this.userPoolId)
                            .withGroupName(UserRole.STUDENT.name())
                            .withLimit(50));

            return listUsersInGroupResult
                    .getUsers().stream()
                    .map(user -> objectMapper.convertValue(user, Student.class)).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Error retrieving all students: " + e.getMessage());
        }

        return Collections.emptyList();
    }

    public Student findUserByUsername(String username) {
        try {
            AdminGetUserResult adminGetUserResult = cognitoClient.adminGetUser(
                    new AdminGetUserRequest()
                            .withUserPoolId(this.userPoolId)
                            .withUsername(username));

            Student student = new Student();
            student.setUsername(adminGetUserResult.getUsername());
            student.setAttributes(adminGetUserResult.getUserAttributes());
            return student;

        } catch (Exception e) {
            System.out.println("Error retrieving all students: " + e.getMessage());
        }

        return null;
    }

}
