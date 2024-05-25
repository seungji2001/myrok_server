package com.example.myrok.controller;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.RecordDTO;
import com.example.myrok.dto.RecordDeleteDTO;
import com.example.myrok.dto.RecordResponseDTO;
import com.example.myrok.dto.RecordUpdateDTO;
import com.example.myrok.service.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

    @PostMapping("/records")
    public ResponseEntity<Long> save( @RequestBody @Valid RecordDTO recordDTO){
        Record savedRecord=recordService.save(recordDTO);
        return new ResponseEntity<>(savedRecord.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/delete/{recordId}")
    public ResponseEntity<Record> delete(@PathVariable("recordId") Long recordId, @RequestBody @Valid RecordDeleteDTO recordDeleteDTO){
        Long recordWriterId = recordDeleteDTO.recordWriterId();
        recordService.deleteUpdate(recordId,recordWriterId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/records/{recordId}")
    public ResponseEntity<Long> update( @PathVariable("recordId") Long recordId, @RequestBody @Valid RecordUpdateDTO recordUpdatedDTO){
        Record updatedRecord=recordService.update(recordId,recordUpdatedDTO);
        return new ResponseEntity<>(updatedRecord.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/records/{recordId}")
    public ResponseEntity<RecordResponseDTO> get(@PathVariable("recordId") Long recordId){
        RecordResponseDTO readRecord = recordService.read(recordId);
        return new ResponseEntity<>(readRecord, HttpStatus.OK);
    }

}
