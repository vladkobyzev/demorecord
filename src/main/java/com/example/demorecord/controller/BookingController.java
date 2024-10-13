package com.example.demorecord.controller;

import com.example.demorecord.dto.request.booking.AddBookingRequest;
import com.example.demorecord.dto.response.BookingFullResponse;
import com.example.demorecord.service.booking.BookingApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingApiService bookingApiService;

    @PostMapping
    public BookingFullResponse addBooking(final @RequestBody @Validated AddBookingRequest request) {
        return bookingApiService.addBooking(request);
    }
}
