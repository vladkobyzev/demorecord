package com.example.demorecord.controller;

import com.example.demorecord.dto.filter.BookFilter;
import com.example.demorecord.dto.request.book.AddBookRequest;
import com.example.demorecord.dto.request.book.EditBookRequest;
import com.example.demorecord.dto.response.BookFullResponse;
import com.example.demorecord.service.book.BookApiService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookApiService bookService;

    @GetMapping
    public Page<BookFullResponse> getBooks(final @ParameterObject BookFilter filter,
                                           @SortDefault(sort = "title", direction = Sort.Direction.ASC)
                                           final Pageable pageable) {
        return bookService.getAllBooks(filter,pageable);
    }

    @GetMapping("/{id}")
    public BookFullResponse getBookById(final @PathVariable UUID id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookFullResponse addBook(final @RequestBody @Validated AddBookRequest request) {
        return bookService.addBook(request);
    }

    @PutMapping()
    public BookFullResponse editBook(final @RequestBody @Validated EditBookRequest request) {
        return bookService.editBook(request);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(final @PathVariable UUID id) {
        bookService.deleteBook(id);
    }
}
