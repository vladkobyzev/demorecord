package com.example.demorecord.service.book.impl;

import com.example.demorecord.dto.filter.BookFilter;
import com.example.demorecord.model.Book;
import com.example.demorecord.repository.BookRepository;
import com.example.demorecord.service.book.BookReadService;
import com.example.demorecord.util.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookReadServiceImpl implements BookReadService {

    private final BookRepository bookRepository;

    @Override
    public Page<Book> getAllBooks(BookFilter filter, Pageable pageable) {
        final var spec = getSpecification(filter);

        return bookRepository.findAll(spec, pageable);
    }

    @Override
    public Book getBookById(UUID id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    private Specification<Book> getSpecification(BookFilter filter) {
        Specification<Book> spec = null;

        switch (filter.getMatchType().getEqual()) {
            case EXACT:
                spec = BookSpecification.hasExactGenres(filter.getGenreIds().getIn());
                break;
            case ALL:
                spec = BookSpecification.hasAllGenres(filter.getGenreIds().getIn());
                break;
            case ANY:
                spec = BookSpecification.hasAnyGenres(filter.getGenreIds().getIn());
                break;
            default:
                throw new IllegalArgumentException("Unknown search type");
        }

        if (Objects.nonNull(filter.getTitle().getEqual())) {
            Specification<Book> titleSpec = BookSpecification.hasTitle(filter.getTitle().getEqual());
            spec = spec.and(titleSpec);
        }

        return spec;
    }
}
