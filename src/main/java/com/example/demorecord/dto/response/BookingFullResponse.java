package com.example.demorecord.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record BookingFullResponse(UUID bookId, UUID userId, LocalDateTime startTime, LocalDateTime endTime) {
}
