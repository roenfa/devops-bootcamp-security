package com.workshop.awscognitoidp.config;

import com.workshop.awscognitoidp.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
        public static final String SIGNUP_URL = "/api/auth/sign-up";
        public static final String SIGN_IN_URL = "/api/auth/sign-in";
        public static final String USERS_URL = "/api/users";
        public static final String TRAINERS_URL = "/api/trainers";
        public static final String STUDENTS_URL = "/api/students";
        // public static final String BOOTCAMPS_URL = "/api/bootcamps";
        // public static final String SUBJECTS_URL = "/api/subjects";

        @Autowired
        private JwtRequestFilter jwtRequestFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {

                List<String> permitAllEndpointList = Arrays.asList(SIGNUP_URL, SIGN_IN_URL);
                List<String> secondTierEndpointList = Arrays.asList(USERS_URL);
                List<String> firstTierEndpointList = Arrays.asList(TRAINERS_URL);
                List<String> allAuthEndpointList = Arrays.asList(STUDENTS_URL);

                http
                        .cors()
                        .and()
                        .csrf().disable()
                        .authorizeRequests(expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry
                                .antMatchers(permitAllEndpointList
                                        .toArray(new String[permitAllEndpointList.size()]))
                                .permitAll()
                                .antMatchers(secondTierEndpointList
                                        .toArray(new String[secondTierEndpointList.size()]))
                                .hasAuthority("SCOPE_ADMIN")
                                .antMatchers(firstTierEndpointList
                                        .toArray(new String[firstTierEndpointList.size()]))
                                .hasAnyAuthority("SCOPE_ADMIN", "SCOPE_TRAINER")
                                .antMatchers(allAuthEndpointList
                                        .toArray(new String[allAuthEndpointList.size()]))
                                .hasAnyAuthority("SCOPE_ADMIN", "SCOPE_TRAINER", "SCOPE_STUDENT")
                                .anyRequest()
                                .authenticated())
                        .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                            .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        ));

                http.addFilterBefore(jwtRequestFilter, BearerTokenAuthenticationFilter.class);
        }

        @Bean
        public JwtAuthenticationConverter jwtAuthenticationConverter() {
                JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
                grantedAuthoritiesConverter.setAuthoritiesClaimName("custom:role");

                JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
                jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
                return jwtAuthenticationConverter;
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