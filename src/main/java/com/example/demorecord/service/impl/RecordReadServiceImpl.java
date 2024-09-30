package com.example.demorecord.service.impl;

import com.example.demorecord.model.Record;
import com.example.demorecord.rep.RecordRepository;
import com.example.demorecord.service.RecordReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecordReadServiceImpl implements RecordReadService {

    private final RecordRepository recordRepository;

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public Record getRecordById(UUID id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
    }
}
