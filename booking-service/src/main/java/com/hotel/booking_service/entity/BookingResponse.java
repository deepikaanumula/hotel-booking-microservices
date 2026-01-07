package com.hotel.booking_service.entity;


public class BookingResponse {

    private Booking booking;
    private UserDTO user;

    public BookingResponse(Booking booking, UserDTO user) {
        this.booking = booking;
        this.user = user;
    }

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
    
}
