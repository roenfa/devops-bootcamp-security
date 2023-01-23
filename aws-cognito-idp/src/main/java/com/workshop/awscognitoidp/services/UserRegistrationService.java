package com.workshop.awscognitoidp.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.workshop.awscognitoidp.exceptions.CognitoUserException;
import com.workshop.awscognitoidp.helpers.UserState;
import com.workshop.awscognitoidp.models.UserDetail;
import com.workshop.awscognitoidp.models.UserSignUpRequest;
import com.workshop.awscognitoidp.services.practice.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import java.util.UUID;

@Service
public class UserRegistrationService {

    @Value(value = "${aws.cognito.userPoolId}")
    private String userPoolId;
    @Value(value = "${aws.cognito.clientId}")
    private String clientId;

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserDetailService userDetailService;

    public UserRegistrationService(UserDetailService userDetailService){
        this.userDetailService = userDetailService;
    }

    public void signUp(UserSignUpRequest userSignUpRequest) throws CognitoUserException {

        try {

            AttributeType emailAttr = new AttributeType().withName("email").withValue(userSignUpRequest.getEmail());
            AttributeType emailVerifiedAttr = new AttributeType().withName("email_verified").withValue("true");

            AdminCreateUserRequest userRequest = new AdminCreateUserRequest()
                    .withUserPoolId(userPoolId).withUsername(userSignUpRequest.getUsername())
                    .withTemporaryPassword(userSignUpRequest.getPassword())
                    .withUserAttributes(emailAttr, emailVerifiedAttr)

                    .withMessageAction(MessageActionType.SUPPRESS)
                    .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL);

            AdminCreateUserResult createUserResult = cognitoClient.adminCreateUser(userRequest);

            System.out.println("User " + createUserResult.getUser().getUsername()
                    + " is created. Status: " + createUserResult.getUser().getUserStatus());

            // Disable force change password during first login
            AdminSetUserPasswordRequest adminSetUserPasswordRequest = new AdminSetUserPasswordRequest()
                    .withUsername(userSignUpRequest.getUsername())
                    .withUserPoolId(userPoolId)
                    .withPassword(userSignUpRequest.getPassword()).withPermanent(true);

            cognitoClient.adminSetUserPassword(adminSetUserPasswordRequest);

            // CREATE NEW LOCAL USER
            UserDetail userDetail = userSingUpRequestToUserDetail(userSignUpRequest);
            userDetailService.saveUserDetail(userDetail);
            
        } catch (AWSCognitoIdentityProviderException e) {
            System.out.println("***********AWSCognitoIdentityProviderException*************");
            System.out.println(e.getErrorMessage());
        } catch (Exception e) {
            System.out.println("Setting user password");
        }
    }

    private UserDetail userSingUpRequestToUserDetail(UserSignUpRequest userSignUpRequest) {
        try {
            return new UserDetail(
                userSignUpRequest.getUsername(),
                userSignUpRequest.getEmail(),
                // userSignUpRequest.getPassword(),
                passwordEncoder.encode(userSignUpRequest.getPassword()),
                UserState.UNCONFIRMED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    /*
     * private String createTemporaryPassword() {
     * return UUID.randomUUID().toString();
     * }
     */
}
