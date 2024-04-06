package com.example.myrok.service;

import com.example.myrok.domain.Record;
import com.example.myrok.domain.RecordTag;
import com.example.myrok.domain.Tag;
import com.example.myrok.exception.NotFoundException;
import com.example.myrok.repository.RecordTagRepository;
import com.example.myrok.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordTagServiceImpl implements RecordTagService{

    @Autowired
    RecordTagRepository recordTagRepository;
    @Autowired
    TagRepository tagRepository;
    @Override
    public List<RecordTag> save(List<String> tagList,Record record){
        List<RecordTag> recordTagList=new ArrayList<>();
        for(String tagName : tagList) {
            Tag tag=tagRepository.findByTagName(tagName)
                    .orElseThrow(() -> new NotFoundException("존재하지 않는 태그입니다."));
            RecordTag recordTag = RecordTag.builder()
                    .record(record)
                    .tag(tag)
                    .build();
            RecordTag savedRecordTag = recordTagRepository.save(recordTag);
            recordTagList.add(savedRecordTag);
        }
        return recordTagList;
    }
    @Override
    public void delete(Long id){
        List<RecordTag> recordTags = recordTagRepository.findAllByRecordId(id);
        for (RecordTag recordTag : recordTags) {
            recordTag.delete();
            recordTagRepository.save(recordTag);
        }

    }
}
