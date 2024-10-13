package com.example.demorecord.dto.response;

import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record BookFullResponse(UUID id, String title, Set<GenreFullResponse> genres, Set<BookingFullResponse> booking) {
}
