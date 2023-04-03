package com.example.chatbot.exception;

public class QuotaException extends RuntimeException{
   public QuotaException(String msg){
        super(msg);
    }
}
