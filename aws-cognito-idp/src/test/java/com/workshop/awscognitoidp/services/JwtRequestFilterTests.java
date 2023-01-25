package com.workshop.awscognitoidp.services;

import com.workshop.awscognitoidp.security.filters.JwtRequestFilter;
import com.workshop.awscognitoidp.security.service.IJwtValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JwtRequestFilterTests {

    @Mock
    private JwtRequestFilter jwtRequestFilter;
    @Test
    public void Should_Success_When_Filter_Is_Called_Validate_Token() throws ServletException, IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        doNothing().when(this.jwtRequestFilter).doFilterInternal(httpServletRequest, httpServletResponse, filterChain);

        this.jwtRequestFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
        verify(this.jwtRequestFilter).doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
    }
}
