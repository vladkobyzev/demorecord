package com.example.demorecord.service.book.impl;

import com.example.demorecord.dto.filter.BookFilter;
import com.example.demorecord.dto.request.book.AddBookRequest;
import com.example.demorecord.dto.request.book.EditBookRequest;
import com.example.demorecord.dto.response.BookFullResponse;
import com.example.demorecord.model.Book;
import com.example.demorecord.service.book.BookApiService;
import com.example.demorecord.service.book.BookControlService;
import com.example.demorecord.service.book.BookReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class BookApiServiceImpl implements BookApiService {
    private final BookControlService bookControlService;
    private final BookReadService bookReadService;

    @Override
    public Page<BookFullResponse> getAllBooks(BookFilter filter, Pageable pageable) {
        return bookReadService.getAllBooks(filter, pageable).map(this::toFullResponse);
    }

    @Override
    public BookFullResponse getBookById(UUID id) {
        var record = bookReadService.getBookById(id);

        return toFullResponse(record);
    }

    @Override
    public BookFullResponse addBook(AddBookRequest request) {
        final var createdBook = bookControlService.addBook(request);
        return toFullResponse(createdBook);
    }

    @Override
    public BookFullResponse editBook(EditBookRequest request) {
        final var updatedBook = bookControlService.editBook(request);
        return toFullResponse(updatedBook);
    }

    @Override
    public void deleteBook(UUID id) {
        bookControlService.deleteBook(id);
    }

    private BookFullResponse toFullResponse(Book book) {
        return BookFullResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .build();
    }
}
