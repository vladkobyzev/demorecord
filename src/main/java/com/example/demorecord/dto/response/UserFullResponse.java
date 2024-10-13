package com.example.demorecord.dto.response;

import java.util.UUID;

public record UserFullResponse(UUID id, String username, String password) {
}
