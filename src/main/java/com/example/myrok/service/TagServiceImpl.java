package com.example.myrok.service;

import com.example.myrok.domain.RecordTag;
import com.example.myrok.domain.Tag;
import com.example.myrok.exception.CustomException;
import com.example.myrok.repository.TagRepository;
import com.example.myrok.type.ErrorCode;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagRepository tagRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Tag save(String tagName){

        Optional<Tag> Tag = tagRepository.findByTagName(tagName);

        if (Tag.isPresent()) {
            // 존재하는 태그의 경우, count 증가 후 저장
            Tag tag = Tag.get();
            tag.incrementCount();
            return tagRepository.save(tag);
        } else {
            // 존재하지 않는 태그의 경우, 새로운 태그 생성 후 저장
            return tagRepository.save(new Tag(tagName, 1, false));
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<RecordTag> recordTagList){
        //Tag Count 감소, 0이라면 삭제
        //deleted 여부도 검사해야될듯
        for (RecordTag recordTag : recordTagList) {
            String tagName= recordTag.getTag().getTagName();
            Tag tag = tagRepository.findByTagName(tagName)
                    .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 태그입니다. name: " + tagName));
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
