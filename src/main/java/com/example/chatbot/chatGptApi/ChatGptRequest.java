package com.example.chatbot.chatGptApi;

import lombok.Data;

@Data
public class ChatGptRequest {
    private String prompt;
    private String model;



    public ChatGptRequest(String prompt) {
        this.prompt = prompt;
        this.model = new String("text-davinci-003");

    }


}
