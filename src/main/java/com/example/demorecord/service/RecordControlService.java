package com.example.demorecord.service;

import com.example.demorecord.dto.request.CreateRecordRequest;
import com.example.demorecord.dto.request.EditRecordRequest;
import com.example.demorecord.model.Record;

import java.util.UUID;

public interface RecordControlService {
    Record createRecord(CreateRecordRequest request) ;

    Record updateRecord(EditRecordRequest request);

    void deleteRecord(UUID id);
}
