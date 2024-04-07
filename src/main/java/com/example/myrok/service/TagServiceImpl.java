package com.example.myrok.service;

import com.example.myrok.domain.RecordTag;
import com.example.myrok.domain.Tag;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.TagRepository;
import com.example.myrok.type.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagRepository tagRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag save(String tagName){
        Tag tag = tagRepository.findByTagName(tagName)
                .orElseGet(() -> tagRepository.save(new Tag(tagName,0,false)));
        // count 증가
        tag.incrementCount();
        tagRepository.save(tag);
        return tag;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<RecordTag> recordTagList){
        //Tag Count 감소, 0이라면 삭제
        //deleted 여부도 검사해야될듯
        for (RecordTag recordTag : recordTagList) {
            Tag tag= recordTag.getTag();
            if (tag.getDeleted()) {
                throw new CustomException(ErrorCode.DELETED_TAG_CODE, HttpStatus.BAD_REQUEST);
            }
            tag.decrementCount();
            if (tag.getCount() <= 0) {
                tag.delete();
            }
            tagRepository.save(tag);
        }

    }
}
