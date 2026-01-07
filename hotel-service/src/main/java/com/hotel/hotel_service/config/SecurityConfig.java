package com.hotel.hotel_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//	    http
//	        .csrf(csrf -> csrf.disable())
//	        .cors(Customizer.withDefaults())
//	        .authorizeHttpRequests(auth -> auth
//
//	            // ✅ Swagger (REQUIRED)
//	            .requestMatchers(
//	                "/swagger-ui/**",
//	                "/swagger-ui.html",
//	                "/v3/api-docs/**"
//	            ).permitAll()
//
//	            // ✅ Allow CORS preflight
//	            // CORS
//	            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//
//	            // 🌍 PUBLIC
//	            .requestMatchers(HttpMethod.GET, "/hotels").permitAll()
//
//	            // 🧪 DEV ONLY: allow POST from Swagger
//	            .requestMatchers(HttpMethod.POST, "/hotels").permitAll()
//
//	            // 🔒 INTERNAL ONLY (ALL OTHERS)
//	            .requestMatchers("/hotels/**")
//	            .access((authentication, context) -> {
//	                String internal = context.getRequest().getHeader("X-INTERNAL-CALL");
//	                return new AuthorizationDecision("true".equals(internal));
//	            })
//
//	            .anyRequest().denyAll()
//	        );
//
//	    return http.build();
//	}
    
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .cors(Customizer.withDefaults())
	        .authorizeHttpRequests(auth -> auth

	            // Swagger
	            .requestMatchers(
	                "/swagger-ui/**",
	                "/swagger-ui.html",
	                "/v3/api-docs/**"
	            ).permitAll()

	            // CORS preflight
	            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

	            // Public API
	            .requestMatchers(HttpMethod.GET, "/hotels").permitAll()

	            // TEMP: Allow all POST for testing
	            .requestMatchers(HttpMethod.POST, "/hotels/**").permitAll()

	            // TEMP: Allow all PUT / DELETE for testing
	            .requestMatchers(HttpMethod.PUT, "/hotels/**").permitAll()
	            .requestMatchers(HttpMethod.DELETE, "/hotels/**").permitAll()

	            // Everything else
	            .anyRequest().permitAll()
	        );

	    return http.build();
	}


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
