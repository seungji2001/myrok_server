package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.RecordDTO;
import com.example.myrok.dto.RecordResponseDTO;

import java.util.List;

public interface RecordService {
    Record save(RecordDTO recordDTO);

    void deleteUpdate(Long id);

}
