package com.example.demorecord.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record RecordFullResponse(UUID id, String data) {
}
