package com.example.demorecord.service.impl;

import com.example.demorecord.dto.request.CreateRecordRequest;
import com.example.demorecord.dto.request.EditRecordRequest;
import com.example.demorecord.model.Record;
import com.example.demorecord.rep.RecordRepository;
import com.example.demorecord.service.RecordControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RecordControlServiceImpl implements RecordControlService {
    private final RecordRepository recordRepository;

    @Override
    public Record createRecord(CreateRecordRequest request) {
        Record record = Record.builder()
                .data(request.data())
                .build();

        return recordRepository.save(record);
    }

    @Override
    public Record updateRecord(EditRecordRequest request) {
        var record = recordRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Record not found"));

        record.setData(request.newData());
        return recordRepository.save(record);
    }

    @Override
    public void deleteRecord(UUID id) {
        recordRepository.deleteById(id);
    }
}
