package com.example.demorecord.service.book;

import com.example.demorecord.dto.filter.BookFilter;
import com.example.demorecord.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookReadService {
    Page<Book> getAllBooks(BookFilter filter, Pageable pageable);

    Book getBookById(UUID id);
}
