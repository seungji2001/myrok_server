package com.example.myrok.repository.search;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.classtype.RecordDTO;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.example.myrok.dto.pagination.PageResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecordSearch {
    PageResponseDto<RecordDTO.RecordListObject> search(PageRequestDto pageRequestDto, String searchValue, String tagValue, Long projectId);
}
