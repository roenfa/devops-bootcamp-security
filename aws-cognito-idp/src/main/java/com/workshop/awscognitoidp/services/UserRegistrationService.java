package com.workshop.awscognitoidp.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.workshop.awscognitoidp.crud.services.UserServiceImp;
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

    @Autowired
    private UserServiceImp userService;

    public void signUp(UserSignUpRequest userSignUpRequest) throws CognitoUserException {

        try {
            AttributeType nameAttr =
                    new AttributeType().withName("name").withValue(userSignUpRequest.getName());
            AttributeType emailAttr =
                    new AttributeType().withName("email").withValue(userSignUpRequest.getEmail());
            AttributeType emailVerifiedAttr =
                    new AttributeType().withName("email_verified").withValue("true");
            AttributeType roleAttr =
                    new AttributeType().withName("custom:role").withValue(userSignUpRequest.getRole());

            AdminCreateUserRequest userRequest = new AdminCreateUserRequest()
                    .withUserPoolId(userPoolId).withUsername(userSignUpRequest.getUsername())
                    .withTemporaryPassword(userSignUpRequest.getPassword())
                    .withUserAttributes(nameAttr, emailAttr, emailVerifiedAttr, roleAttr)
                    .withMessageAction(MessageActionType.SUPPRESS)
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

            AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(userRequest);

            System.out.println("User " + createUserResult.getUser().getUsername()
                    + " is created. Status: " + createUserResult.getUser().getUserStatus());
            

            userService.insertUser(userSignUpRequest);
            // Disable force change password during first login
            // AdminSetUserPasswordRequest adminSetUserPasswordRequest =
            //         new AdminSetUserPasswordRequest().withUsername(userSignUpRequest.getUsername())
            //                 .withUserPoolId(userPoolId)
            //                 .withPassword(userSignUpRequest.getPassword()).withPermanent(true);

            // cognitoClient.adminSetUserPassword(adminSetUserPasswordRequest);

        } catch (AWSCognitoIdentityProviderException e) {
            System.out.println(e.getErrorMessage());
        } catch (Exception e) {
            System.out.println("Setting user password");
        }
    }

    private String createTemporaryPassword() {
        return UUID.randomUUID().toString();
    }
}

