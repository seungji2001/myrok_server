package com.example.myrok.service.openAi;

import com.example.myrok.dto.ClovaDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClovaSummary {


    @Value("${naver.clova.api.client}")
    public String clientId;
    @Value("${naver.clova.api.secret}")
    public String secretKey;

    public ClovaDto.ResponseDto get(ClovaDto.RequestDto requestDto){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", secretKey);

        JSONObject jsonObject = new JSONObject();

        // JSON 객체에 데이터 추가
        try {
            jsonObject.put("document", new JSONObject()
                    .put("title", requestDto.getTitle())
                    .put("content", requestDto.getContent()))
                    .put( "option", new JSONObject()
                    .put("language", "ko")
                    );
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // JSON 객체를 문자열로 변환
        String jsonString = jsonObject.toString();

        // 결과 출력
        System.out.println(jsonString);

        HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

        ResponseEntity<String> response = restTemplate.exchange("https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize", HttpMethod.POST, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());
            return ClovaDto.ResponseDto.builder()
                    .summary(String.valueOf(jsonNode.get("summary")))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
