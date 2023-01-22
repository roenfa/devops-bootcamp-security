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
    @Value(value = "${aws.access-key}")
    private String accessKey;
    @Value(value = "${aws.access-secret}")
    private String secretKey;
    @Value(value = "${aws.default-region}")
    private String awsRegion;

    @Value("${aws.cognito.jwk}")
    private String jwkUrl;

    @Bean
    public AWSCognitoIdentityProvider cognitoClient() {

        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(awsRegion)
                .build();
    }

    @Bean
    public AwsCognitoRSAKeyProvider startRSAProvider() {
        return new AwsCognitoRSAKeyProvider(jwkUrl);
    }

}