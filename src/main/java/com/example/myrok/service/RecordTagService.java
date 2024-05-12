package com.example.myrok.service;

import com.example.myrok.domain.Record;

public interface RecordTagService {
    void save(Long projectId, Record record, String tagName);
    void delete(Long recordId);
}
