package com.example.myrok.service;

import com.example.myrok.dto.DashBoardDTO;
import com.example.myrok.dto.TagDTO;
import com.example.myrok.repository.RecordTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashBoardServiceImpl implements DashBoardService{

    @Autowired
    private RecordTagRepository recordTagRepository;

    @Override
    public DashBoardDTO.TagResponseDTO getTagInfo(Long projectId){
        List<TagDTO> tags = recordTagRepository.findTagNameAndCountByProjectIdAndDeletedIsFalse(projectId);
        List<TagDTO> topTags = tags.stream()
                .limit(4)
                .toList(); // Java 8 호환성을 위해 toList() 대신 collect(Collectors.toList()) 사용
        // 전체 태그 갯수
        long sum = recordTagRepository.countByProjectIdAndDeletedIsFalse(projectId);
        long tagSum = 0;
        // tag 비율 계산
        for (TagDTO tagDto : topTags) {
            tagSum += tagDto.getPercentage(); // getPercentage 가 tag 개수임
            tagDto.setPercentage((long)((tagDto.getPercentage() * 100.0) / sum)); // 비율 계산해서 set, double 로 계산한 뒤 long 으로 캐스팅
        }
        Long etcPercentage = (long)(((sum - tagSum) * 100.0) / sum);

        DashBoardDTO.TagResponseDTO tagResponseDTO = DashBoardDTO.TagResponseDTO.builder()
                .etcPercentage(etcPercentage)
                .tags(topTags) // 상위 4개만 반환 (4 개 이하라면?)
                .build();
        return tagResponseDTO;
    }

}
