package com.example.demorecord.service.genre.impl;

import com.example.demorecord.dto.request.genre.AddGenreRequest;
import com.example.demorecord.dto.request.genre.EditGenreRequest;
import com.example.demorecord.dto.response.GenreFullResponse;
import com.example.demorecord.model.Book;
import com.example.demorecord.model.Genre;
import com.example.demorecord.service.genre.GenreApiService;
import com.example.demorecord.service.genre.GenreControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class GenreApiServiceImpl implements GenreApiService {
    private final GenreControlService genreControlService;
    @Override
    public GenreFullResponse addGenre(AddGenreRequest request) {
        final var genre = genreControlService.addGenre(request);
        return toFullResponse(genre);
    }

    @Override
    public GenreFullResponse editGenre(EditGenreRequest request) {
        final var genre = genreControlService.editGenre(request);
        return toFullResponse(genre);
    }

    @Override
    public void deleteGenre(UUID id) {
        genreControlService.deleteGenre(id);
    }

    private GenreFullResponse toFullResponse(Genre genre) {
        return GenreFullResponse.builder()
                .id(genre.getId())
                .title(genre.getTitle())
                .books(genre.getBooks().stream().map(Book::getId).collect(Collectors.toSet()))
                .build();
    }
}
