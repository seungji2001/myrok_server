package com.example.myrok.dto.record;

import lombok.*;

public class RecordClass {

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class RecordListObject {
        Long recordId;
        String recordName;
        String recordDate;
        String recordWriterName;
    }

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ResponseDTO {
       Long id;
       String summary;
    }
}
