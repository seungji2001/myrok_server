package com.example.myrok.dto.classtype.event;

import com.example.myrok.domain.Record;

public class RecordSavedEvent {
    private Record record;

    public RecordSavedEvent(Record record) {
        this.record = record;
    }
    public Record getRecord() {
        return record;
    }
}