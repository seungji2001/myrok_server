package com.example.myrok.controller;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.recordtype.RecordDTO;
import com.example.myrok.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Operation(
            summary = "프로젝트 회의록 목록을 가져옵니다",
            description = "프로젝트 회의록 목록을 가져옵니다"
    )
    @ApiResponse(
            responseCode = "200",
            description = "프로젝트 회의록 목록을 가져왔습니다."
    )
    @PostMapping("/list")
    public ResponseEntity<List<com.example.myrok.dto.classtype.RecordDTO.RecordListObject>> getRecordList(Long projectId){
        return new ResponseEntity<>(recordService.getRecords(projectId), HttpStatus.OK);
    }
}
