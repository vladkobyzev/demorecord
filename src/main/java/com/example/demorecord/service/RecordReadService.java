package com.example.demorecord.service;

import com.example.demorecord.model.Record;

import java.util.List;
import java.util.UUID;

public interface RecordReadService {
    List<Record> getAllRecords();

    Record getRecordById(UUID id);
}
