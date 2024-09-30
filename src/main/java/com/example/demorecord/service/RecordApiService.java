package com.example.demorecord.service;

import com.example.demorecord.dto.request.CreateRecordRequest;
import com.example.demorecord.dto.request.EditRecordRequest;
import com.example.demorecord.dto.response.RecordFullResponse;

import java.util.List;
import java.util.UUID;

public interface RecordApiService {
    RecordFullResponse createRecord(CreateRecordRequest request);
    RecordFullResponse getRecordById(UUID id);
    List<RecordFullResponse> getAllRecords();
    RecordFullResponse updateRecord(EditRecordRequest request);
     void deleteRecord(UUID id);
}
