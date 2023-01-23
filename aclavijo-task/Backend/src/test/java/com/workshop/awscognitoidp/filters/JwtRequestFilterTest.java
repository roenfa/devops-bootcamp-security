package com.workshop.awscognitoidp.filters;

import com.workshop.awscognitoidp.services.JWTDecoder;
import com.workshop.awscognitoidp.services.JwtValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JwtRequestFilterTest {

    @BeforeEach
    public void setup(){

    }

    @Test
    public void Should_Success_When_Filter_is_called() throws ServletException, IOException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer valid_token");
        when(request.getRequestURI()).thenReturn("/url");

        JwtValidator jwtValidator = mock(JwtValidator.class);
        when(jwtValidator.validateJwtToken("valid_token")).thenReturn(true);

        JWTDecoder JWTDecoder = mock(JWTDecoder.class);
        when(JWTDecoder.getRole("valid_token")).thenReturn("trainer");

        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();
        jwtRequestFilter.jwtValidator = jwtValidator;
        jwtRequestFilter.JWTDecoder = JWTDecoder;

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        verify(jwtValidator).validateJwtToken("valid_token");
        verify(JWTDecoder).getRole("valid_token");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void Should_return_same_value_of_token() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer token");
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter();

        String result = jwtRequestFilter.parseToken(request);

        assertEquals("token", result);
    }


}
