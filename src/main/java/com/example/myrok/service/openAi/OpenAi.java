package com.example.myrok.service.openAi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class OpenAi {

    public static String threadId;
    public static String assistentId;

    @Value("${open_ai.api.key}")
    public static String apiKey;
    public void makeRequest() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v1");

        String payload = "{\n" +
                "    \"instructions\": \"You are a personal math tutor. When asked a question, write and run Python code to answer the question.\",\n" +
                "    \"name\": \"Math Tutor\",\n" +
                "    \"tools\": [{\"type\": \"code_interpreter\"}],\n" +
                "    \"model\": \"gpt-3.5-turbo\"\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.exchange("https://api.openai.com/v1/assistants", HttpMethod.POST, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();


// JSON 문자열을 JsonNode로 파싱
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

// "id" 필드의 값을 가져옴
        assistentId = jsonNode.get("id").asText();

        System.out.println(response);
        System.out.println(threadId);
    }


    public void makeThread(){
//        curl https://api.openai.com/v1/threads \
//        -H "Content-Type: application/json" \
//        -H "Authorization: Bearer $OPENAI_API_KEY" \
//        -H "OpenAI-Beta: assistants=v1" \
//        -d ''d
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v1");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.openai.com/v1/threads";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response);
        ObjectMapper objectMapper = new ObjectMapper();


// JSON 문자열을 JsonNode로 파싱
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

// "id" 필드의 값을 가져옴
        threadId = jsonNode.get("id").asText();

        System.out.println(response);
        System.out.println(threadId);

    }

    public void makeThreadRun() throws JSONException {
//        @Headers("OpenAI-Beta: assistants=v1")
//        @POST("/v1/threads/{thread_id}/runs")
//        Single<Run> createRun(@Path("thread_id") String threadId, @Body RunCreateRequest runCreateRequest);

        String url = "https://api.openai.com/v1/threads/" + threadId + "/runs";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v1");

        Map<String, Object> payload = new HashMap<>();
        payload.put("assistant_id",assistentId);
        payload.put("stream",true);
        payload.put("additional_instructions", null); // JSON null

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        System.out.println(response);

    }

    public void makeMessage(){
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";

        // JSON 형식의 요청 본문 생성
        String jsonBody = "{\"role\": \"user\", \"content\": \"How does AI work? Explain it in simple terms.\"}";

        // HttpClient 생성

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v1");

        String payload = "{\n" +
                "    \"role\": \"user\",\n" +
                "    \"content\": \"How does AI work? Explain it in simple terms.\"\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);

        url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        System.out.println(response.getBody());
    }

    public void getMessage(){
        String url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("OpenAI-Beta", "assistants=v1");


        HttpEntity<String> entity = new HttpEntity<>(headers);

        url = "https://api.openai.com/v1/threads/" + threadId + "/messages";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println(response.getBody());
    }
}
