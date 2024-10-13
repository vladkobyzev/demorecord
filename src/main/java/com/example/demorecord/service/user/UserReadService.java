package com.example.demorecord.service.user;

import com.example.demorecord.model.User;

import java.util.UUID;

public interface UserReadService {
    User getUserById(UUID id);
}
