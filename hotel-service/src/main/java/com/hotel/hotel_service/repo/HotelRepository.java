package com.hotel.hotel_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.hotel_service.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}

