package com.hotel.user_service;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(
    servers = {
        @Server(url = "http://localhost:8080/user-service", description = "API Gateway URL")
    }
)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
