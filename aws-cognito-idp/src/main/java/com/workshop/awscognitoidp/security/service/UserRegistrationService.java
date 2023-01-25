package com.workshop.awscognitoidp.security.service;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.workshop.awscognitoidp.security.exceptions.CognitoUserException;
import com.workshop.awscognitoidp.security.models.UserRole;
import com.workshop.awscognitoidp.security.models.UserSignUpRequest;
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

    public void signUp(UserSignUpRequest userSignUpRequest, UserRole userRole) throws CognitoUserException {

        try {

            AttributeType emailAttr =
                    new AttributeType().withName("email").withValue(userSignUpRequest.getEmail());
            AttributeType emailVerifiedAttr =
                    new AttributeType().withName("email_verified").withValue("true");

            AdminCreateUserRequest userRequest = new AdminCreateUserRequest()
                    .withUserPoolId(userPoolId).withUsername(userSignUpRequest.getUsername())
                    .withTemporaryPassword(userSignUpRequest.getPassword())
                    .withUserAttributes(emailAttr, emailVerifiedAttr)
                    .withMessageAction(MessageActionType.SUPPRESS)
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

            AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(userRequest);
            System.out.println("User " + createUserResult.getUser().getUsername()
                    + " is created. Status: " + createUserResult.getUser().getUserStatus());

            this.setUserRole(createUserResult.getUser().getUsername(), userRole);

            // Disable force change password during first login
            AdminSetUserPasswordRequest adminSetUserPasswordRequest =
                    new AdminSetUserPasswordRequest().withUsername(userSignUpRequest.getUsername())
                            .withUserPoolId(userPoolId)
                            .withPassword(userSignUpRequest.getPassword()).withPermanent(true);

            cognitoClient.adminSetUserPassword(adminSetUserPasswordRequest);

        } catch (AWSCognitoIdentityProviderException e) {
            System.out.println(e.getErrorMessage());
        } catch (Exception e) {
            System.out.println("Setting user password");
        }
    }

    public void setUserRole(String username, UserRole userRole) {
        try{
            AdminAddUserToGroupRequest addUserToGroupRequest = new AdminAddUserToGroupRequest()
                    .withUserPoolId(this.userPoolId)
                    .withUsername(username)
                    .withGroupName(userRole.name());
            cognitoClient.adminAddUserToGroup(addUserToGroupRequest);
        }catch (Exception e) {
            System.out.println("Set user default group fails: " + e.getMessage());
        }
    }

    private String createTemporaryPassword() {
        return UUID.randomUUID().toString();
    }
}
