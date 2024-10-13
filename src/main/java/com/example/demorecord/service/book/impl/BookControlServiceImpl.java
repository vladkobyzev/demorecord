package com.example.demorecord.service.book.impl;

import com.example.demorecord.dto.request.book.AddBookRequest;
import com.example.demorecord.dto.request.book.EditBookRequest;
import com.example.demorecord.model.Book;
import com.example.demorecord.repository.BookRepository;
import com.example.demorecord.repository.GenreRepository;
import com.example.demorecord.service.book.BookControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookControlServiceImpl implements BookControlService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    @Override
    public Book addBook(AddBookRequest request) {
        final var genres = new HashSet<>(genreRepository.findAllById(request.genres()));

        if(request.genres().size() != genres.size()) {
            throw new RuntimeException("Bad Request: Жанр не найден");
        }

        final var book = Book.builder()
                .title(request.title())
                .genres(genres)
                .build();

        return bookRepository.save(book);
    }

    @Override
    public Book editBook(EditBookRequest request) {
        var record = bookRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Book not found"));

        record.setTitle(request.title());
        record.setGenres(request.genres());
        return bookRepository.save(record);
    }

    @Override
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}
