package com.hotel.booking_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // Swagger
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html"
                ).permitAll()

                // Gateway only
                .requestMatchers("/bookings/**")
                .access((authentication, context) -> {
                    String internal =
                        context.getRequest().getHeader("X-INTERNAL-CALL");
                    return new AuthorizationDecision("true".equals(internal));
                })

                .anyRequest().denyAll()
            );

        return http.build();
    }
}

