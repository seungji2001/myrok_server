package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.domain.RecordTag;
import com.example.myrok.domain.Tag;

public interface RecordTagService {
    void save(Record record, Tag tag);
    void delete(Long id);
}
