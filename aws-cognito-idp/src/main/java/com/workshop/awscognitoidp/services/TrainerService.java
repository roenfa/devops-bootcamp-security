package com.workshop.awscognitoidp.services;


import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.ListUsersInGroupRequest;
import com.amazonaws.services.cognitoidp.model.ListUsersInGroupResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.awscognitoidp.models.Trainer;
import com.workshop.awscognitoidp.security.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerService {

    @Value(value = "${aws.cognito.userPoolId}")
    private String userPoolId;

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public List<Trainer> retrieveAllTrainers() {
        try {
            ListUsersInGroupResult listUsersInGroupResult = cognitoClient.listUsersInGroup(
                    new ListUsersInGroupRequest()
                            .withUserPoolId(this.userPoolId)
                            .withGroupName(UserRole.TRAINER.name())
                            .withLimit(50));

            return listUsersInGroupResult
                    .getUsers().stream()
                    .map( user -> objectMapper.convertValue(user, Trainer.class)).collect(Collectors.toList());
        }catch (Exception e) {
            System.out.println("Error retrieving all trainers: " + e.getMessage());
        }

        return Collections.emptyList();
    }

}
