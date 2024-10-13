package com.example.demorecord.service.book;

import com.example.demorecord.dto.filter.BookFilter;
import com.example.demorecord.dto.request.book.AddBookRequest;
import com.example.demorecord.dto.request.book.EditBookRequest;
import com.example.demorecord.dto.response.BookFullResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookApiService {
    Page<BookFullResponse> getAllBooks(BookFilter filter, Pageable pageable);
    BookFullResponse getBookById(UUID id);
    BookFullResponse addBook(AddBookRequest request);
    BookFullResponse editBook(EditBookRequest request);
     void deleteBook(UUID id);
}
