package com.example.myrok.component;


import com.example.myrok.domain.Record;
import com.example.myrok.dto.classtype.event.RecordSavedEvent;
import com.example.myrok.repository.RecordRepository;
import com.example.myrok.service.openAi.ChatCompletionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RecordEventListener {

    private final ChatCompletionService chatCompletionService;
    private final RecordRepository recordRepository;

    @Autowired
    public RecordEventListener(ChatCompletionService chatCompletionService, RecordRepository recordRepository) {
        this.chatCompletionService = chatCompletionService;
        this.recordRepository = recordRepository;
    }

    @Async
    @EventListener
    @Transactional
    public void handleRecordSavedEvent(RecordSavedEvent event) {
        // RecordSavedEvent를 수신하면 makeRecordSummary 메서드를 호출하여 두 번째 작업 수행
        Record record = event.getRecord();
        String recordContent = record.getRecordContent();
        String summary = chatCompletionService.chatCompletions(recordContent);
        if(!StringUtils.hasLength(summary)){
            throw new RuntimeException();
        }
        record.updateSummary(summary);
        recordRepository.save(record);
    }
}
