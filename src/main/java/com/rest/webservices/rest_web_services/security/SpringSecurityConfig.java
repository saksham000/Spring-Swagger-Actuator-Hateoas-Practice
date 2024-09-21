package com.rest.webservices.rest_web_services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        http.authorizeHttpRequests(
            auth -> auth
            .requestMatchers("/",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/actuator",
            "/actuator/info",
            "/actuator/**"
            ).permitAll()
            .anyRequest().authenticated()
        );

        http.httpBasic(withDefaults());

        http.csrf(csrf -> csrf.disable());
        
        return http.build();
    }
}
