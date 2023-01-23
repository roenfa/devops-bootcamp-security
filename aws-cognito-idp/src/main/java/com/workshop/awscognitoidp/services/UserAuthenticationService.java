package com.workshop.awscognitoidp.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.workshop.awscognitoidp.exceptions.CognitoUserException;
import com.workshop.awscognitoidp.helpers.UserState;
import com.workshop.awscognitoidp.models.UserDetail;
import com.workshop.awscognitoidp.models.UserSignInRequest;
import com.workshop.awscognitoidp.models.UserSignInResponse;
import com.workshop.awscognitoidp.services.practice.UserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserAuthenticationService {
    @Value(value = "${aws.cognito.userPoolId}")
    private String userPoolId;
    @Value(value = "${aws.cognito.clientId}")
    private String clientId;

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    private final UserDetailService userDetailService;

    public UserAuthenticationService(UserDetailService userDetailService){
        this.userDetailService = userDetailService;
    }

    public UserSignInResponse signIn(UserSignInRequest userSignInRequest) throws CognitoUserException {

        UserSignInResponse userSignInResponse = new UserSignInResponse();

        final Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", userSignInRequest.getUsername());
        authParams.put("PASSWORD", userSignInRequest.getPassword());

        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId(clientId)
                .withUserPoolId(userPoolId).withAuthParameters(authParams);

        try {
            AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);

            AuthenticationResultType authenticationResult = null;

            if (result.getChallengeName() != null && !result.getChallengeName().isEmpty()) {

                System.out.println("Challenge Name is " + result.getChallengeName());

                if (result.getChallengeName().contentEquals("NEW_PASSWORD_REQUIRED")) {
                    if (userSignInRequest.getPassword() == null) {
                        throw new CognitoUserException(
                                "User must change password " + result.getChallengeName());
                    }

                    final Map<String, String> challengeResponses = new HashMap<>();
                    challengeResponses.put("USERNAME", userSignInRequest.getUsername());
                    challengeResponses.put("PASSWORD", userSignInRequest.getPassword());
                    // add new password
                    challengeResponses.put("NEW_PASSWORD", userSignInRequest.getNewPassword());

                    final AdminRespondToAuthChallengeRequest request = new AdminRespondToAuthChallengeRequest()
                            .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                            .withChallengeResponses(challengeResponses)
                            .withClientId(clientId).withUserPoolId(userPoolId)
                            .withSession(result.getSession());

                    AdminRespondToAuthChallengeResult resultChallenge = cognitoClient
                            .adminRespondToAuthChallenge(request);
                    authenticationResult = resultChallenge.getAuthenticationResult();

                    userSignInResponse.setAccessToken(authenticationResult.getAccessToken());
                    userSignInResponse.setIdToken(authenticationResult.getIdToken());
                    userSignInResponse.setRefreshToken(authenticationResult.getRefreshToken());
                    userSignInResponse.setExpiresIn(authenticationResult.getExpiresIn());
                    userSignInResponse.setTokenType(authenticationResult.getTokenType());

                } else {
                    throw new CognitoUserException(
                            "User has other challenge " + result.getChallengeName());
                }
            } else {
                System.out.println("User has no challenge");
                Optional<UserDetail> userDetail = userDetailService.findUserDetailByUsername(userSignInRequest.getUsername());
                userDetail.ifPresent(user -> {
                    if(user.getState() == UserState.UNCONFIRMED)
                        user.setState(UserState.CONFIRMED); 
                    
                    System.out.println("user change: "+user);
                });

                authenticationResult = result.getAuthenticationResult();

                userSignInResponse.setAccessToken(authenticationResult.getAccessToken());
                userSignInResponse.setIdToken(authenticationResult.getIdToken());
                userSignInResponse.setRefreshToken(authenticationResult.getRefreshToken());
                userSignInResponse.setExpiresIn(authenticationResult.getExpiresIn());
                userSignInResponse.setTokenType(authenticationResult.getTokenType());
            }

        } catch (InvalidParameterException e) {
            throw new CognitoUserException(e.getErrorMessage());
        } catch (Exception e) {
            throw new CognitoUserException(e.getMessage());
        }

        return userSignInResponse;
    }
}
