package com.hotel.booking_service.service;

import java.time.temporal.ChronoUnit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.booking_service.client.HotelClient;
import com.hotel.booking_service.client.UserClient;
import com.hotel.booking_service.entity.Booking;
import com.hotel.booking_service.entity.BookingResponse;
import com.hotel.booking_service.entity.HotelDTO;
import com.hotel.booking_service.entity.UserDTO;
import com.hotel.booking_service.repo.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;
    @Autowired
    private UserClient userClient;

    @Autowired
    private HotelClient hotelClient;

    // USER → Book hotel
    public Booking bookHotel(Booking booking) {

        // Call Hotel Service using Feign
        HotelDTO hotel = hotelClient.getHotelById(booking.getHotelId());

        long nights = ChronoUnit.DAYS.between(
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        );

        if (nights <= 0) {
            throw new RuntimeException("Invalid booking dates");
        }

        booking.setTotalPrice(hotel.getPricePerNight() * nights);
        booking.setStatus("BOOKED");

        return repo.save(booking);
    }

    // USER → Get bookings by userId
    public List<Booking> getBookingsByUser(Long userId) {
        return repo.findByUserId(userId);

    }

    // USER → Cancel booking
    public Booking cancelBooking(Long bookingId) {
        Booking booking = repo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus("CANCELLED");
        return repo.save(booking);
    }

    // ADMIN → Get all bookings
    public List<Booking> getAllBookings() {
        return repo.findAll();
    }
    
    public List<BookingResponse> getAllBookingsWithUser() {

        return repo.findAll()
            .stream()
            .map(booking -> {

                System.out.println("USER ID = " + booking.getUserId());

                UserDTO user =
                    userClient.getUserById(booking.getUserId());

                return new BookingResponse(booking, user);
            })
            .toList();
    }


}
