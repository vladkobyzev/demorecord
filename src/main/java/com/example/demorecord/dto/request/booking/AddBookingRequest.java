package com.example.demorecord.dto.request.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AddBookingRequest(@NotNull UUID bookId, @NotNull UUID userId) {
}
