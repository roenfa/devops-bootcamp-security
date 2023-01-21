package com.workshop.awscognitoidp.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.NotAuthorizedException;
import com.workshop.awscognitoidp.exceptions.CognitoUserException;
import com.workshop.awscognitoidp.models.UserSignInRequest;
import com.workshop.awscognitoidp.models.UserSignInResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAuthenticationServiceTest {

    @Test
    public void signIn_validCredentials_authenticationSucceeds() throws CognitoUserException {
        UserSignInRequest userSignInRequest = new UserSignInRequest();
        userSignInRequest.setUsername("test_user");
        userSignInRequest.setPassword("test_password");

        AWSCognitoIdentityProvider cognitoClient = mock(AWSCognitoIdentityProvider.class);

        AdminInitiateAuthResult initiateAuthResult = new AdminInitiateAuthResult();
        initiateAuthResult.setAuthenticationResult(new AuthenticationResultType().withAccessToken("access_token")
                .withIdToken("id_token")
                .withRefreshToken("refresh_token")
                .withExpiresIn(3600)
                .withTokenType("Bearer"));

        when(cognitoClient.adminInitiateAuth(any(AdminInitiateAuthRequest.class))).thenReturn(initiateAuthResult);

        UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
        userAuthenticationService.cognitoClient = cognitoClient;
        userAuthenticationService.userPoolId = "test_user_pool_id";
        userAuthenticationService.clientId = "test_client_id";

        UserSignInResponse result = userAuthenticationService.signIn(userSignInRequest);

        assertEquals("access_token", result.getAccessToken());
        assertEquals("id_token", result.getIdToken());
        assertEquals("refresh_token", result.getRefreshToken());
        assertEquals(3600, result.getExpiresIn());
        assertEquals("Bearer", result.getTokenType());
    }
}
