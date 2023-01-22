package com.workshop.awscognitoidp.config;

import com.workshop.awscognitoidp.services.AwsCognitoRSAKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;


@Configuration
public class CognitoConfig {

    @Value("${aws.cognito.jwk}")
    private String jwkUrl;

    @Bean
    public AWSCognitoIdentityProvider cognitoClient() {
        return AWSCognitoIdentityProviderClientBuilder.defaultClient();
    }

    @Bean
    public AwsCognitoRSAKeyProvider startRSAProvider() {
        return new AwsCognitoRSAKeyProvider(jwkUrl);
    }

}