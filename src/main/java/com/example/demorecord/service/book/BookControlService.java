package com.example.demorecord.service.book;

import com.example.demorecord.dto.request.book.AddBookRequest;
import com.example.demorecord.dto.request.book.EditBookRequest;
import com.example.demorecord.model.Book;

import java.util.UUID;

public interface BookControlService {
    Book addBook(AddBookRequest request) ;

    Book editBook(EditBookRequest request);

    void deleteBook(UUID id);
}
