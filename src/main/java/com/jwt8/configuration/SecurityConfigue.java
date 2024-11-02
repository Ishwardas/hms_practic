package com.jwt8.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

@Configuration
public class SecurityConfigue {

private JWTFilter jwtFilter;

    public SecurityConfigue(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().disable();
httpSecurity.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();
//        httpSecurity.authorizeHttpRequests()
//                .requestMatchers("/api/v1/users/signup","/api/v1/users/signup-property-owner","/api/v1/users/login").permitAll()
//                .requestMatchers("/api/v1/country/addCountry").hasAnyRole("ADMIN","OWNER").anyRequest().authenticated();
        return httpSecurity.build();
    }
}
