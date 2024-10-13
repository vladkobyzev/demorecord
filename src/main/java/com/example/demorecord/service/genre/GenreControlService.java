package com.example.demorecord.service.genre;

import com.example.demorecord.dto.request.genre.AddGenreRequest;
import com.example.demorecord.dto.request.genre.EditGenreRequest;
import com.example.demorecord.model.Genre;

import java.util.UUID;

public interface GenreControlService {
    Genre addGenre(AddGenreRequest request);

    Genre editGenre(EditGenreRequest request);

    void deleteGenre(UUID id);
}
