package com.example.demorecord.dto.request.genre;

import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record EditGenreRequest(@NotNull UUID id, String title, Set<UUID> books) {
}
