package com.example.demorecord.dto.filter.primitive;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class QueryFilter<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -1464447072639081572L;

    protected T equal;

    protected T notEqual;

    protected Collection<T> in;

    protected Collection<T> notIn;

    protected Boolean isNull;
}
