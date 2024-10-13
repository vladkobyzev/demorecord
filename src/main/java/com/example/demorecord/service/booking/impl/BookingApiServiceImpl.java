package com.example.demorecord.service.booking.impl;

import com.example.demorecord.dto.request.booking.AddBookingRequest;
import com.example.demorecord.dto.response.BookingFullResponse;
import com.example.demorecord.model.Booking;
import com.example.demorecord.service.booking.BookingApiService;
import com.example.demorecord.service.booking.BookingControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BookingApiServiceImpl implements BookingApiService {
    private final BookingControlService bookingControlService;

    @Override
    public BookingFullResponse addBooking(AddBookingRequest request) {
        final var booking = bookingControlService.addBooking(request);
        return toFullResponse(booking);
    }

    private BookingFullResponse toFullResponse(Booking booking) {
        return BookingFullResponse.builder()
                .bookId(booking.getBook().getId())
                .userId(booking.getUser().getId())
                .startTime(booking.getStartTime())
                .endTime(booking.getEndTime())
                .build();
    }
}
