package com.example.demorecord.service.booking;

import com.example.demorecord.dto.request.booking.AddBookingRequest;
import com.example.demorecord.dto.response.BookingFullResponse;

public interface BookingApiService {
    BookingFullResponse addBooking(AddBookingRequest request);
}
