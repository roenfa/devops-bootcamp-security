package com.workshop.awscognitoidp.filters;

import com.workshop.awscognitoidp.config.ConfigurationConstants;
import com.workshop.awscognitoidp.services.JwtData;
import com.workshop.awscognitoidp.services.JwtValidator;
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
    JwtData jwtData;
    @Autowired
    JwtValidator jwtValidator;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if ( !this.isPublicUrl(request.getRequestURI())) {
            String token = this.parseToken(request);
            logger.info("Extracted token: " + token);

            boolean result = jwtValidator.validateJwtToken(token);
            String Role =  jwtData.getTokenRole(token);
            logger.info("Jwt Token is valid? " + result);
            System.out.println(Role);
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
        return ConfigurationConstants.permitAllEndpointList.contains(incomingUri);
    }
}
