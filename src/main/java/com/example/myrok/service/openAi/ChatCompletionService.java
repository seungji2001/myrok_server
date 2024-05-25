package com.example.myrok.service.openAi;
import com.example.myrok.dto.classtype.chat.ChatRequest;
import com.example.myrok.dto.classtype.chat.Message;
import com.example.myrok.infrastructure.ChatCompletionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ChatCompletionService {

    private final ChatCompletionClient chatCompletionClient;
    private final static String ROLE_USER = "user";
    private final static String MODEL = "gpt-3.5-turbo";

    @Value("${open_ai.api.key}")
    private String apiKey;

    public String chatCompletions(final String question) {

        Message message = Message.builder()
                .role(ROLE_USER)
                .content(question)
                .build();

        ChatRequest chatRequest = ChatRequest.builder()
                .model(MODEL)
                .messages(Collections.singletonList(message))
                .build();

        return chatCompletionClient
                .chatCompletions("Bearer " + apiKey, chatRequest)
                .getChoices()
                .stream()
                .findFirst()
                .orElseThrow()
                .getMessage()
                .getContent();
    }
}