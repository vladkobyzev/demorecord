package com.example.demorecord.dto.request.book;

import com.example.demorecord.model.Genre;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record EditBookRequest(@NotNull UUID id, String title, Set<Genre> genres) {
}
