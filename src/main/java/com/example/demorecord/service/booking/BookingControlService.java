package com.example.demorecord.service.booking;

import com.example.demorecord.dto.request.booking.AddBookingRequest;
import com.example.demorecord.model.Booking;

public interface BookingControlService {
    Booking addBooking(AddBookingRequest request);
}
