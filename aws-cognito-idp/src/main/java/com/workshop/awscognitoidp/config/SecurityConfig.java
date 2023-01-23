package com.workshop.awscognitoidp.config;

import com.workshop.awscognitoidp.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry
                        .antMatchers(ConfigurationConstants.permittedEndpointList.toArray(new String[0]))
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(getJwtAuthenticationConverter());

        http.addFilterBefore(jwtRequestFilter, BearerTokenAuthenticationFilter.class);
    }


    public Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> foundAuthorities = ( List<String>) jwt.getClaims().get("cognito:groups");
            if(Objects.isNull(foundAuthorities))
                return null;
            return foundAuthorities.stream().map(authority -> new SimpleGrantedAuthority("ROLE_" + authority)).collect(Collectors.toList());
        });
        return converter;
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