package com.example.demorecord.controller;

import com.example.demorecord.dto.request.genre.AddGenreRequest;
import com.example.demorecord.dto.request.genre.EditGenreRequest;
import com.example.demorecord.dto.response.GenreFullResponse;
import com.example.demorecord.service.genre.GenreApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreApiService genreService;

    @PostMapping
    public GenreFullResponse addGenre(final @RequestBody @Validated AddGenreRequest request) {
        return genreService.addGenre(request);
    }

    @PutMapping()
    public GenreFullResponse editGenre(final @RequestBody @Validated EditGenreRequest request) {
        return genreService.editGenre(request);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(final @PathVariable UUID id) {
        genreService.deleteGenre(id);
    }
}
