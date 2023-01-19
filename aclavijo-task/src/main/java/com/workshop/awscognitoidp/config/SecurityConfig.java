package com.workshop.awscognitoidp.config;

import com.workshop.awscognitoidp.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String SIGNUP_URL = "/api/users/sign-up";
    public static final String SIGN_IN_URL = "/api/users/sign-in";

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        List<String> permitAllEndpointList = Arrays.asList(SIGNUP_URL, SIGN_IN_URL);

        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry
                        .antMatchers(permitAllEndpointList
                                .toArray(new String[permitAllEndpointList.size()]))
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer()
                .jwt();

        http.addFilterBefore(jwtRequestFilter, BearerTokenAuthenticationFilter.class);
    }
}

/** Angular o React (opcional)
 * PRACTICA - Enviar un pull request hasta el Viernes
 * Roles: Trainer y Student
 * Models: Student
 * Student necesita verificar las notas de sus materias
 * Rol: Student -> materias -> notas
 * Rol: Trainer -> students -> subir notas
 * Rol: Trainer -> trainers -> bootcamp -> subir notas
 * Rol: Admin -> students -> list
 * Rol: Admin -> students -> notas
 * Rol: Admin -> trainers -> list
 */