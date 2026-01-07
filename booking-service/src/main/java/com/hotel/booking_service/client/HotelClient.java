package com.hotel.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hotel.booking_service.config.FeignConfig;
import com.hotel.booking_service.entity.HotelDTO;

@FeignClient(name = "hotel-service", configuration = FeignConfig.class)
public interface HotelClient {

    @GetMapping("/hotels/{id}")
    HotelDTO getHotelById(@PathVariable Long id);
}
