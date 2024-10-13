package com.example.demorecord.util;

import com.example.demorecord.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class BookingCleanerService {
    private final BookingRepository bookingRepository;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void cleanExpiredReservations() {
        bookingRepository.deleteExpiredReservations(LocalDateTime.now());
    }
}
