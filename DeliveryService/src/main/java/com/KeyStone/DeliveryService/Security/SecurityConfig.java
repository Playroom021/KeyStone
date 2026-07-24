package com.KeyStone.DeliveryService.Security;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMethodSecutity(prePostEnabled=true)

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
        return new JWTAuthenticationFilter(jwtUtil, customUseDetailsService);
    }
}
