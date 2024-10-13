package com.example.demorecord.dto.filter.primitive;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@NoArgsConstructor
@SuperBuilder
public class StringFilter extends QueryFilter<String> {

    @Serial
    private static final long serialVersionUID = -85321715739768L;
}
