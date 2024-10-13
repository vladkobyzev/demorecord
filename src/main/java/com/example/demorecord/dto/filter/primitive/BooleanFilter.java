package com.example.demorecord.dto.filter.primitive;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@NoArgsConstructor
@SuperBuilder
public class BooleanFilter extends QueryFilter<Boolean> {

    @Serial
    private static final long serialVersionUID = -8532171573768499768L;
}
