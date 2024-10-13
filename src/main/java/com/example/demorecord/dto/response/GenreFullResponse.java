package com.example.demorecord.dto.response;

import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record GenreFullResponse(UUID id, String title, Set<UUID> books) {
}
