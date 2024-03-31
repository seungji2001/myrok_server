package com.example.myrok.service;

import com.example.myrok.domain.*;
import com.example.myrok.domain.Record;
import com.example.myrok.dto.RecordDTO;
import com.example.myrok.exception.NotFoundException;
import com.example.myrok.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements RecordService{

    @Autowired
    private final RecordRepository recordRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final TagRepository tagRepository;
    @Autowired
    private final RecordTagRepository recordTagRepository;
    @Autowired
    private final MemberRecordRepository memberRecordRepository;

    @Override
    public Record save(RecordDTO recordDTO){

        // 멤버 리스트 & 태그 리스트 받아와서 Record 저장
        List<String> tags = recordDTO.tagList();
        List<Long> members=recordDTO.memberList();

        List<Member> memberList= memberRepository.findAllById(members);
        List<Tag> tagList= tagRepository.findAllByTagNameIn(tags);

        Record record= recordDTO.toEntity(memberList,tagList);

        Record savedRecord = recordRepository.save(record);

        // Tag 데이터베이스 유무 검사 후 Tag 저장 & 매핑 테이블도 저장
        for (String tagName : tags) {
            Tag tag = tagRepository.findByTagName(tagName)
                    .orElseGet(() -> tagRepository.save(new Tag(tagName,0)));

            // count 증가
            tag.incrementCount();
            tagRepository.save(tag);

            // Record 와 Tag 사이의 연결을 위한 RecordTag 객체 생성 및 저장
            RecordTag recordTag = RecordTag.builder()
                    .record(record)
                    .tag(tag)
                    .build();
            recordTagRepository.save(recordTag);

        }

        // Member 와 Record 매핑
        for (Long memberId : members) {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new NotFoundException("존재하지 않는 팀원입니다."));

            MemberRecord memberRecord = MemberRecord.builder()
                    .record(record)
                    .member(member)
                    .build();
            memberRecordRepository.save(memberRecord);

        }

        return savedRecord;
    }

    @Override
    public Boolean deleteUpdate(Long id) {
        try {
            // 회의록 삭제
            Record record = recordRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("존재하지 않는 회의록입니다."));
            record.delete();

            //회의록 안의 태그 리스트 Count 감소 & Count==0 이라면 삭제
            List<Tag> tagList = record.getTagList();
            for (Tag tag : tagList) {
                tag.decrementCount();
                if (tag.getCount() == 0) {
                    tag.delete();
                }
                tagRepository.save(tag);
            }

            //MemberRecord 매핑객체 삭제
            List<MemberRecord> memberRecords = memberRecordRepository.findAllByRecordId(record.getId());
            for (MemberRecord memberRecord : memberRecords) {
                memberRecord.delete();
                memberRecordRepository.save(memberRecord);
            }

            //RecordTag 매핑객체 삭제
            List<RecordTag> recordTags = recordTagRepository.findAllByRecordId(record.getId());
            for (RecordTag recordTag : recordTags) {
                recordTag.delete();
                recordTagRepository.save(recordTag);
            }

            return true;
        }  catch (NotFoundException e) {
            System.out.println("존재하지 않는 회의록 삭제 시도");
            return false;
        } catch (Exception e) {
            return false;
        }
    }


}
