package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.ChatGptService;
import com.example.chatbot.chatGptApi.ChatGptRequest;
import com.example.chatbot.dataAccesLayer.CommentRepository;
import com.example.chatbot.entity.Comment;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Data
public class ChatGptServiceImpl implements ChatGptService {

    private final String API_KEY = "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";
    private final String API_URL = "https://api.openai.com/v1/completions";

    @Override
    public String generateText( String prompt) {
        RestTemplate restTemplates = new RestTemplate();
        restTemplates.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        ChatGptRequest request = new ChatGptRequest("Classify the sentiment in this comment" + prompt);
        HttpEntity<ChatGptRequest> requestHttpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<String> responseEntity = restTemplates.exchange(API_URL, HttpMethod.POST, requestHttpEntity, String.class);
        String responseData = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseData);
            String responseAnswer = jsonNode.get("choices").get(0).get("text").asText();
            return responseAnswer;


        } catch (Exception e) {
            e.printStackTrace();
            return "there is a problem";
        }

    }
}
