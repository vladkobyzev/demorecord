package com.example.demorecord.dto.filter.primitive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UUIDFilter extends QueryFilter<UUID> {

    @Serial
    private static final long serialVersionUID = 9098130882839626041L;
}
