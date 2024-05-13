package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.classtype.RecordDTO;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.dto.pagination.PageResponseDto;

import java.util.List;
import com.example.myrok.dto.RecordResponseDTO;
import com.example.myrok.dto.RecordUpdateDTO;

import java.util.List;

public interface RecordService {
    Record save(com.example.myrok.dto.recordtype.RecordDTO recordDTO);

    void deleteUpdate(Long id);

    List<RecordDTO.RecordListObject> getRecords(Long projectId);

    PageResponseDto<RecordDTO.RecordListObject> getRecords(PageRequestDto pageRequestDto, Long projectId);

    PageResponseDto<RecordDTO.RecordListObject> getRecordsBySearch(PageRequestDto pageRequestDto, String searchValue, String tagName);

    Record update(Long id, RecordUpdateDTO recordDTO);

}
