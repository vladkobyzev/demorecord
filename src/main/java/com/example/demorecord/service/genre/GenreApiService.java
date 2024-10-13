package com.example.demorecord.service.genre;

import com.example.demorecord.dto.request.genre.AddGenreRequest;
import com.example.demorecord.dto.request.genre.EditGenreRequest;
import com.example.demorecord.dto.response.GenreFullResponse;

import java.util.UUID;

public interface GenreApiService {
    GenreFullResponse addGenre(AddGenreRequest request);

    GenreFullResponse editGenre(EditGenreRequest request);

    void deleteGenre(UUID id);
}
