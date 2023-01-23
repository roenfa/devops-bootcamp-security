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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtRequestFilterTests {
    private JwtRequestFilter jwtFilter;
    private MockHttpServletRequest  httpServletRequestMock;
    private MockHttpServletResponse httpServletResponseMock;
    private MockFilterChain filterChainMock;

    @Mock
    private IJwtValidator jwtValidatorMock;
//    private jwtValidatorMock
    private OncePerRequestFilter filter;
    private List<OncePerRequestFilter> invocations;

    @BeforeEach
    public void setup() {
        jwtFilter = new JwtRequestFilter(jwtValidatorMock);
//        Mock(JwtValidator)
        httpServletRequestMock = new MockHttpServletRequest();
        httpServletRequestMock.setRequestURI("test");
        httpServletRequestMock.addHeader("Authorization", "Bearer test");

        httpServletResponseMock = new MockHttpServletResponse();
        filterChainMock = new MockFilterChain();

//        jwtValidatorMock = mock(JwtValidator.class);

        this.invocations = new ArrayList<>();
        this.filter = new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                JwtRequestFilterTests.this.invocations.add(this);
                filterChain.doFilter(request, response);
            }
        };
    }

    @Test
    public void Should_Success_When_Filter_Is_Called_Validate_Token() throws ServletException, IOException {
        when(jwtValidatorMock.validateJwtToken(anyString())).thenReturn(true);

        jwtFilter.doFilterInternal(httpServletRequestMock, httpServletResponseMock, filterChainMock);

        assertThat(this.httpServletResponseMock.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
