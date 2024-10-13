package com.example.demorecord.util;

import com.example.demorecord.model.Book;
import com.example.demorecord.model.Genre;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;
import java.util.UUID;

public class BookSpecification {

    public static Specification<Book> hasExactGenres(Collection<UUID> genreIds) {
        return (root, query, criteriaBuilder) -> {
            Join<Book, Genre> genres = root.join("genres");

            Predicate genreMatch = genres.get("id").in(genreIds);

            query.groupBy(root.get("id"))
                    .having(criteriaBuilder.equal(criteriaBuilder.count(genres.get("id")), genreIds.size()));

            return genreMatch;
        };
    }

    public static Specification<Book> hasAllGenres(Collection<UUID> genreIds) {
        return (root, query, criteriaBuilder) -> {
            Join<Book, Genre> genres = root.join("genres");

            // Подзапрос для проверки, что каждая книга содержит все переданные жанры
            Predicate[] predicates = genreIds.stream()
                    .map(genreId -> criteriaBuilder.isMember(genreId, genres.get("id")))
                    .toArray(Predicate[]::new);

            return criteriaBuilder.and(predicates);
        };
    }

    public static Specification<Book> hasAnyGenres(Collection<UUID> genreIds) {
        return (root, query, criteriaBuilder) -> {
            Join<Book, Genre> genres = root.join("genres");

            return genres.get("id").in(genreIds);
        };
    }

    public static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }
}
