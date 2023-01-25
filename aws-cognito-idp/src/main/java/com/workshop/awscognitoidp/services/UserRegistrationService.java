package com.workshop.awscognitoidp.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.workshop.awscognitoidp.exceptions.CognitoUserException;
import com.workshop.awscognitoidp.models.UserSignUpRequest;
import com.workshop.awscognitoidp.models.practice.User;
import com.workshop.awscognitoidp.services.practice.UserService;
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
    private UserService userService;

    public void signUp(UserSignUpRequest userSignUpRequest) throws CognitoUserException {

        try {

            AttributeType emailAttr =
                    new AttributeType().withName("email").withValue(userSignUpRequest.getEmail());
            AttributeType emailVerifiedAttr =
                    new AttributeType().withName("email_verified").withValue("true");
;


            AdminCreateUserRequest userRequest = new AdminCreateUserRequest()
                    .withUserPoolId(userPoolId).withUsername(userSignUpRequest.getUsername())
                    .withTemporaryPassword(userSignUpRequest.getPassword())
                    .withUserAttributes(emailAttr, emailVerifiedAttr)
                    .withMessageAction(MessageActionType.SUPPRESS)
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

            AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(userRequest);

            // Disable force change password during first login
            AdminSetUserPasswordRequest adminSetUserPasswordRequest =
                    new AdminSetUserPasswordRequest().withUsername(userSignUpRequest.getUsername())
                            .withUserPoolId(userPoolId)
                            .withPassword(userSignUpRequest.getPassword()).withPermanent(true);

            cognitoClient.adminSetUserPassword(adminSetUserPasswordRequest);

            // Add user to group based on role
            AdminAddUserToGroupRequest adminAddUserToGroupRequest =
                    new AdminAddUserToGroupRequest().withUserPoolId(userPoolId)
                            .withUsername(userSignUpRequest.getUsername())
                            .withGroupName(userSignUpRequest.getRole());

            AdminAddUserToGroupResult adminAddUserToGroupResult = cognitoClient.adminAddUserToGroup(adminAddUserToGroupRequest);
            if (adminAddUserToGroupResult.getSdkHttpMetadata().getHttpStatusCode() == 200) {
                System.out.println("User " + createUserResult.getUser().getUsername()
                        + " is created. Status: " + createUserResult.getUser().getUserStatus());
                System.out.println("User " + userSignUpRequest.getUsername() + " is added to group " + userSignUpRequest.getRole());
                User user = new User();
                user.setIdUsername(userSignUpRequest.getUsername());
                user.setEmail(userSignUpRequest.getEmail());
                user.setRole(userSignUpRequest.getRole());
                userService.saveUser(user);
                System.out.println("User " + userSignUpRequest.getUsername() + " is added to database");
            }


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

