package com.example.demorecord.dto.request.book;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record AddBookRequest(@NotNull String title, Set<UUID> genres) {
}
