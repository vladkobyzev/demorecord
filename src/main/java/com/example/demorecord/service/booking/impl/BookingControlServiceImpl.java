package com.example.demorecord.service.booking.impl;

import com.example.demorecord.dto.request.booking.AddBookingRequest;
import com.example.demorecord.model.Booking;
import com.example.demorecord.repository.BookingRepository;
import com.example.demorecord.service.book.BookReadService;
import com.example.demorecord.service.booking.BookingControlService;
import com.example.demorecord.service.user.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BookingControlServiceImpl implements BookingControlService {
    private final BookingRepository bookingRepository;
    private final BookReadService bookReadService;
    private final UserReadService userReadService;
    @Override
    public Booking addBooking(AddBookingRequest request) {
        final var book = bookReadService.getBookById(request.bookId());
        final var user = userReadService.getUserById(request.userId());

        if (bookingRepository.existsByBookIdAndUserId(book.getId(), user.getId())) {
            throw new RuntimeException("Book already booking");
        }

        final var startBookingTime = LocalDateTime.now();
        final var endBookingTime = startBookingTime.plusHours(12);

        final var booking = Booking.builder()
                .book(book)
                .user(user)
                .startTime(startBookingTime)
                .endTime(endBookingTime)
                .build();

        return bookingRepository.save(booking);
    }
}
