package com.workshop.awscognitoidp.config;

import com.nimbusds.jose.shaded.json.JSONArray;
import com.nimbusds.jwt.JWTClaimsSet;
import com.workshop.awscognitoidp.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.workshop.awscognitoidp.filters.JwtRequestFilter.BEARER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String SIGNUP_URL = "/api/users/sign-up";
    public static final String SIGN_IN_URL = "/api/users/sign-in";
    private static final String rolesFiled = "cognito:groups";


    @Autowired
    private JwtRequestFilter jwtRequestFilter;



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        List<String> permitAllEndpointList = Arrays.asList(SIGNUP_URL, SIGN_IN_URL);
        //http enables cors for all endpoints by default

        http.cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers(permitAllEndpointList.toArray(new String[permitAllEndpointList.size()])).permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwt -> {
                    Collection<GrantedAuthority> authorities = Arrays.stream(jwt.getClaims().get(rolesFiled).toString().replace("[", "").replace("]", "").replace("\"", "").split(",") )
                            .map(String::trim)
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    return new UsernamePasswordAuthenticationToken(jwt.getSubject(), BEARER + jwt.getTokenValue(), authorities);
                });
        http.addFilterBefore(jwtRequestFilter, BearerTokenAuthenticationFilter.class);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}

/**
 * Angular o React (opcional)
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