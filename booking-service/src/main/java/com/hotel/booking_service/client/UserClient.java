package com.hotel.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.booking_service.config.FeignConfig;
import com.hotel.booking_service.entity.UserDTO;

@FeignClient(
	    name = "user-service",
	    configuration = FeignConfig.class
	)
	public interface UserClient {

	    @GetMapping("/users/{id}")
	    UserDTO getUserById(@PathVariable Long id);
	}

