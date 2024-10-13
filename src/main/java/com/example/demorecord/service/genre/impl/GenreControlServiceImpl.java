package com.example.demorecord.service.genre.impl;

import com.example.demorecord.dto.request.genre.AddGenreRequest;
import com.example.demorecord.dto.request.genre.EditGenreRequest;
import com.example.demorecord.model.Book;
import com.example.demorecord.model.Genre;
import com.example.demorecord.repository.BookRepository;
import com.example.demorecord.repository.GenreRepository;
import com.example.demorecord.service.genre.GenreControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GenreControlServiceImpl implements GenreControlService {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    @Override
    public Genre addGenre(AddGenreRequest request) {
        final var allBooksInNewGenre = new HashSet<Book>();

        if(Objects.nonNull(request.books())) {
            allBooksInNewGenre.addAll(bookRepository.findAllById(request.books()));
        }

        final var genre = Genre.builder()
                .title(request.title())
                .books(allBooksInNewGenre)
                .build();

        return genreRepository.save(genre);
    }

    @Override
    public Genre editGenre(EditGenreRequest request) {
        final var genre = genreRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Genre Not Found"));

        genre.setTitle(request.title());

        final var newGenreBooks = new HashSet<>(bookRepository.findAllById(request.books()));
        genre.setBooks(newGenreBooks);

        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(UUID id) {
        genreRepository.deleteById(id);
    }
}
