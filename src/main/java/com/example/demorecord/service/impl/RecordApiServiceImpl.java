package com.example.demorecord.service.impl;

import com.example.demorecord.dto.request.CreateRecordRequest;
import com.example.demorecord.dto.request.EditRecordRequest;
import com.example.demorecord.dto.response.RecordFullResponse;
import com.example.demorecord.model.Record;
import com.example.demorecord.service.RecordApiService;
import com.example.demorecord.service.RecordControlService;
import com.example.demorecord.service.RecordReadService;
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
    private final RecordControlService recordControlService;
    private final RecordReadService recordReadService;

    @Override
    public List<RecordFullResponse> getAllRecords() {
        return recordReadService.getAllRecords().stream()
                .map(this::toFullResponse).collect(Collectors.toList());
    }

    @Override
    public RecordFullResponse getRecordById(UUID id) {
        var record = recordReadService.getRecordById(id);

        return toFullResponse(record);
    }

    @Override
    public RecordFullResponse createRecord(CreateRecordRequest request) {
        final var createdRecord = recordControlService.createRecord(request);
        return toFullResponse(createdRecord);
    }

    @Override
    public RecordFullResponse updateRecord(EditRecordRequest request) {
        final var updatedRecord = recordControlService.updateRecord(request);
        return toFullResponse(updatedRecord);
    }

    @Override
    public void deleteRecord(UUID id) {
        recordControlService.deleteRecord(id);
    }

    private RecordFullResponse toFullResponse(Record record) {
        return RecordFullResponse.builder()
                .id(record.getId())
                .data(record.getData())
                .build();
    }
}
