package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.RecordDTO;

public interface RecordService {
    Record save(RecordDTO recordDTO);

    Boolean deleteUpdate(Long id);
}
