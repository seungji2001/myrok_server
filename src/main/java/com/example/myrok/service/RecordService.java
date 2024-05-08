package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.classtype.RecordDTO;

import java.util.List;


public interface RecordService {
    Record save(com.example.myrok.dto.recordtype.RecordDTO recordDTO);

    void deleteUpdate(Long id);

    List<RecordDTO.RecordListObject> getRecords(Long projectId);

}
