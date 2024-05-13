package com.example.myrok.repository.search;

import com.example.myrok.domain.QRecord;
import com.example.myrok.domain.QRecordTag;
import com.example.myrok.domain.QTag;
import com.example.myrok.domain.Record;
import com.example.myrok.dto.pagination.PageRequestDto;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Arrays;
import java.util.List;

public class RecordSearchImpl extends QuerydslRepositorySupport implements RecordSearch{

    public RecordSearchImpl() {
        super(Record.class);
    }

    @Override
    public Page<Record> search(PageRequestDto pageRequestDto, String searchValue, String tagValue) {
        QRecord record = QRecord.record;
        QRecordTag recordTag = QRecordTag.recordTag;
        QTag tag = QTag.tag;

        JPQLQuery<Record> query = from(record);
        if (searchValue!= null &&!searchValue.isEmpty()) {
            query.where(record.recordName.like("%" + searchValue + "%"));
        }
        if (tagValue!= null &&!tagValue.isEmpty()) {
            query.select(record)
                    .from(record)
                    .leftJoin(record.recordTagList, recordTag).fetchJoin()
                    .leftJoin(recordTag.tag, tag).fetchJoin()
                    .fetch();
        }


        Pageable pageable = PageRequest.of(pageRequestDto.getPage() -1, pageRequestDto.getSize(), Sort.by("recordDate").descending());

        this.getQuerydsl().applyPagination(pageable,query);

        List<Record> list = query.fetch(); //목록 데이터

        long total = query.fetchCount();

        System.out.println(Arrays.toString(list.toArray()));
        return new PageImpl<>(list, pageable, total);
    }
}
