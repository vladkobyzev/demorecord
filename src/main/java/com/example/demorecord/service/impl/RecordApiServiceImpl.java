package com.example.demorecord.service.impl;

import com.example.demorecord.dto.request.CreateRecordRequest;
import com.example.demorecord.dto.request.EditRecordRequest;
import com.example.demorecord.dto.response.RecordFullResponse;
import com.example.demorecord.model.Record;
import com.example.demorecord.rep.RecordRepository;
import com.example.demorecord.service.RecordApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class RecordApiServiceImpl implements RecordApiService {
    private final RecordRepository recordRepository;

    @Override
    public List<RecordFullResponse> getAllRecords() {
        return  recordRepository.findAll().stream().map(this::toFullResponse).collect(Collectors.toList());
    }

    public RecordFullResponse getRecordById(UUID id) {
        var record = recordRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));

        return toFullResponse(record);
    }

    public RecordFullResponse createRecord(CreateRecordRequest request) {
        Record record = Record.builder()
                .data(request.data())
                .build();

        var savedRecord = recordRepository.save(record);
        return toFullResponse(savedRecord);
    }

    public RecordFullResponse updateRecord(EditRecordRequest request) {
        var record = recordRepository.findById(request.id()).orElseThrow(() -> new RuntimeException("Record not found"));

        record.setData(request.newData());
        var updatedRecord = recordRepository.save(record);
        return toFullResponse(updatedRecord);
    }

    public void deleteRecord(UUID id) {
        recordRepository.deleteById(id);
    }

    private RecordFullResponse toFullResponse(Record record) {
        return RecordFullResponse.builder()
                .id(record.getId())
                .data(record.getData())
                .build();
    }
}
