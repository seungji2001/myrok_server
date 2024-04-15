package com.example.myrok.service;

import com.example.myrok.service.openAi.OpenAi;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OpenAiTests {

    @Autowired
    OpenAi openAi;

    @Test
    public void openAitest() throws JsonProcessingException, JSONException {
        openAi.makeRequest();
        openAi.makeThread();
        openAi.makeMessage();
        openAi.makeThreadRun();
        openAi.getMessage();
    }
}
