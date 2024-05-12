package com.example.myrok.controller;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.RecordDTO;
import com.example.myrok.dto.RecordResponseDTO;
import com.example.myrok.service.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Record> save( @Valid @RequestBody RecordDTO recordDTO){
        Record savedRecord=recordService.save(recordDTO);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    @PostMapping("/delete/{recordId}")
    public ResponseEntity<Record> delete(@PathVariable("recordId") Long id){
        recordService.deleteUpdate(id);
        return ResponseEntity.noContent().build();
    }

}
