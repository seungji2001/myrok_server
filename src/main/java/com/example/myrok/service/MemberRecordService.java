package com.example.myrok.service;

import com.example.myrok.domain.MemberRecord;
import com.example.myrok.domain.Record;

import java.util.List;

public interface MemberRecordService {
    void save(List<Long> members, Record record);
    void delete(Long id);
}
