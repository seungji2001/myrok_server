package com.example.myrok.repository.search;

import com.example.myrok.domain.Record;
import com.example.myrok.dto.pagination.PageRequestDto;
import org.springframework.data.domain.Page;

public interface RecordSearch {
    Page<Record> search(PageRequestDto pageRequestDto, String searchValue, String tagValue, Long projectId);
}
