package com.hms.confi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import java.security.Security;

@Configuration
public class SecurityConfig {

    public JWTFilter JwtFilter;

    public SecurityConfig(JWTFilter jwtFilter){
        this.JwtFilter=jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http
    )throws Exception
    {
        //h(cd)2
        http.csrf().disable().cors().disable();
        http.addFilterBefore(JwtFilter, AuthorizationFilter.class);

        //haap
        http.authorizeHttpRequests().anyRequest().permitAll();

       return http.build();
    }


}
