package com.example.myrok.controller;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.RecordDTO;
import com.example.myrok.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myrok")
public class RecordController {
    @Autowired
    private final RecordService recordService;

    // 회의록 작성 이동
    @GetMapping("/records")
    public void save(){}

    @PostMapping("/records")
    public ResponseEntity<Record> saveOk(@RequestBody RecordDTO recordDTO){
        Record savedRecord=recordService.save(recordDTO);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }
}
