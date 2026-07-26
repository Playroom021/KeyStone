package com.KeyStone.DeliveryService.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Autowired;



@Configuration

@EnableMethodSecurity(prePostEnabled=true)

public class SecurityConfig {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private CustomUseDetailsService customUseDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
        // return new JWTAuthenticationFilter(jwtUtil, customUseDetailsService);
    }
}
