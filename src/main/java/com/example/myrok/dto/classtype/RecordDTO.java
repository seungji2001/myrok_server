package com.example.myrok.dto.classtype;

import lombok.*;

public class RecordDTO {

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
