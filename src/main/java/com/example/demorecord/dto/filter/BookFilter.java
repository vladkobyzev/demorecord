package com.example.demorecord.dto.filter;

import com.example.demorecord.dto.filter.enumeration.BookMatchTypeFilter;
import com.example.demorecord.dto.filter.primitive.StringFilter;
import com.example.demorecord.dto.filter.primitive.UUIDFilter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class BookFilter {
    private UUIDFilter genreIds;
    private BookMatchTypeFilter matchType;
    private StringFilter title;
}
