package com.example.myrok.dto;

import com.example.myrok.domain.Record;
import com.example.myrok.domain.Tag;

public record RecordTagDTO (
        Record record,
        Tag tag
){

}
