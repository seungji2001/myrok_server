package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.domain.RecordTag;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.RecordTagRepository;
import com.example.myrok.type.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordTagServiceImpl implements RecordTagService{

    @Autowired
    RecordTagRepository recordTagRepository;

    @Override
    public RecordTag save(Long projectId, Record record, String tagName)
    {
        RecordTag recordTag = RecordTag.builder()
                .projectId(projectId)
                .record(record)
                .tagName(tagName)
                .build();
        return recordTagRepository.save(recordTag);
    }
    @Override
    public void delete(Long id){
        List<RecordTag> recordTags = recordTagRepository.findAllByRecordId(id);
        for (RecordTag recordTag : recordTags) {
            if (recordTag.getDeleted()){
                throw new CustomException(ErrorCode.DELETED_RT_CODE, HttpStatus.BAD_REQUEST);
            }
            recordTag.delete();
            recordTagRepository.save(recordTag);
        }

    }
}
