package com.workshop.awscognitoidp.security.filters;

import com.workshop.awscognitoidp.security.config.ConfigurationConstants;
import com.workshop.awscognitoidp.security.service.JwtValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Autowired
    private JwtValidator jwtValidator;


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if ( !this.isPublicUrl(request.getRequestURI())) {
            String token = this.parseToken(request);
            logger.info("Extracted token: " + token);

            // TODO: Replace this code with call to am Authorizer Lambda
            boolean result = jwtValidator.validateJwtToken(token);
            logger.info("Jwt Token is valid? " + result);
        }

        filterChain.doFilter(request, response);
    }

    private String parseToken(HttpServletRequest request) {
        final String authorizationValue = request.getHeader(AUTHORIZATION);

        if (authorizationValue != null && authorizationValue.startsWith(BEARER)) {
            return authorizationValue.substring(7);
        }

        return null;
    }

    private boolean isPublicUrl(String incomingUri) {
        return ConfigurationConstants.permittedEndpointList.contains(incomingUri);
    }
}
