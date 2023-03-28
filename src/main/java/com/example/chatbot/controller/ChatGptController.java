package com.example.chatbot.controller;

import com.example.chatbot.chatGptApi.ChatGptRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Controller
public class ChatGptController {
    private final String API_KEY = "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";
    private final String API_URL = "https://api.openai.com/v1/completions";

    @GetMapping("/")
    public String showPage() {

        return "indexes";

    }

    @PostMapping("/")
    public String generateText(Model model, @RequestParam("prompt") String prompt) {
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
            String responseAnswer = jsonNode.get("choices").get(0).get("text").asText();
            model.addAttribute("responseData", responseAnswer);

        } catch (Exception e) {
            e.printStackTrace();
        }
        /* model.addAttribute("response", responseAnswer);*/
        return "indexes";
    }

}
