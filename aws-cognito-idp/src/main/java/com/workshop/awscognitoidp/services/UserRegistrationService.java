package com.workshop.awscognitoidp.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.workshop.awscognitoidp.exceptions.CognitoUserException;
import com.workshop.awscognitoidp.models.UserSignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserRegistrationService {

    @Value(value = "${aws.cognito.userPoolId}")
    private String userPoolId;
    @Value(value = "${aws.cognito.clientId}")
    private String clientId;

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    public String signUp(UserSignUpRequest userSignUpRequest) throws CognitoUserException {

        String userStatus = null;
        try {

            AttributeType emailAttr =
                    new AttributeType().withName("email").withValue(userSignUpRequest.getEmail());
            AttributeType emailVerifiedAttr =
                    new AttributeType().withName("email_verified").withValue("true");

            AdminCreateUserRequest userRequest = new AdminCreateUserRequest()
                    .withUserPoolId(userPoolId).withUsername(userSignUpRequest.getUsername())
                    .withTemporaryPassword(createTemporaryPassword())
                    .withUserAttributes(emailAttr, emailVerifiedAttr)
                    .withMessageAction(MessageActionType.SUPPRESS)
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

            AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(userRequest);

            // TODO: Store in our database the email, username, cognito sub

            userStatus = createUserResult.getUser().getUserStatus();
            System.out.println("User " + createUserResult.getUser().getUsername()
                    + " is created. Status: " + userStatus);

        } catch (AWSCognitoIdentityProviderException e) {
            System.out.println(e.getErrorMessage());
            throw new CognitoUserException("Cognito Provider error: " + e.getErrorMessage());
        } catch (Exception e) {
            System.out.println("Setting user password");
            throw new CognitoUserException("Setting user password");
        }

        return userStatus;
    }

    private String createTemporaryPassword() {
        return UUID.randomUUID().toString();
    }
}

