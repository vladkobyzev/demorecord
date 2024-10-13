package com.example.demorecord.service.user.impl;

import com.example.demorecord.model.User;
import com.example.demorecord.repository.UserRepository;
import com.example.demorecord.service.user.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserReadServiceImpl implements UserReadService {
    private final UserRepository userRepository;
    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
