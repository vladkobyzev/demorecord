package com.example.demorecord.dto.filter.enumeration;

import com.example.demorecord.dto.enumeration.BookMatchType;
import com.example.demorecord.dto.filter.primitive.QueryFilter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@NoArgsConstructor
@SuperBuilder
public class BookMatchTypeFilter extends QueryFilter<BookMatchType> {

    @Serial
    private static final long serialVersionUID = -3436767437191782496L;
}
