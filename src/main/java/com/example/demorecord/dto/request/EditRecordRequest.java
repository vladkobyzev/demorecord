package com.example.demorecord.dto.request;

import java.util.UUID;

public record EditRecordRequest(UUID id, String newData) {
}
