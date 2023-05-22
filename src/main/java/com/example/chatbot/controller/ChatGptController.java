package com.example.chatbot.controller;

import com.example.chatbot.chatGptApi.ChatGptRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
@Data
public class ChatGptController {
    private final String API_KEY = "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";
    private final String API_URL = "https://api.openai.com/v1/completions";
    public static String answer;



    @PostMapping("/mainPage")
    public String generateText( @RequestParam("prompt") String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);
        ChatGptRequest request = new ChatGptRequest("Classify the sentiment in this comment"+prompt);
        HttpEntity<ChatGptRequest> requestHttpEntity = new HttpEntity<>(request, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(API_URL, HttpMethod.POST, requestHttpEntity, String.class);
        String responseData = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(responseData);
            answer = jsonNode.get("choices").get(0).get("text").asText();


        } catch (Exception e) {
            e.printStackTrace();
        }
        /* model.addAttribute("response", responseAnswer);*/
        return "redirect:/mainPage/news";
    }

}
