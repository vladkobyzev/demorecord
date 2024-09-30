package com.example.demorecord.controller;

import com.example.demorecord.dto.request.CreateRecordRequest;
import com.example.demorecord.dto.request.EditRecordRequest;
import com.example.demorecord.dto.response.RecordFullResponse;
import com.example.demorecord.service.impl.RecordApiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class RecordController {

    private final RecordApiServiceImpl recordApiServiceImpl;

    @PostMapping
    public RecordFullResponse createRecord(@RequestBody @Validated CreateRecordRequest request) {
        return recordApiServiceImpl.createRecord(request);
    }

    @GetMapping("/{id}")
    public RecordFullResponse getRecord(@PathVariable UUID id) {
        return recordApiServiceImpl.getRecordById(id);
    }

    @PutMapping("/{id}")
    public Record updateRecord(@RequestBody @Validated EditRecordRequest request) {
        return recordApiServiceImpl.updateRecord(request);
    }

    @DeleteMapping("/{id}")
    public void deleteRecord(@PathVariable UUID id) {
        recordApiServiceImpl.deleteRecord(id);
    }
}
