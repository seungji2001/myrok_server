package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.domain.RecordTag;
import com.example.myrok.domain.Tag;

import java.util.List;

public interface RecordTagService {
    List<RecordTag> save(List<String> tagList,Record record);
    void delete(Long id);
}
