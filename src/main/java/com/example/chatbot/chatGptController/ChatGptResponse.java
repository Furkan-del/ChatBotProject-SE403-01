package com.example.chatbot.chatGptController;

import lombok.Data;

import java.util.List;

@Data
public class ChatGptResponse {
private List<ChatGptChoice> choices;


}
