package com.example.chatbot.business.abstracts;


import com.example.chatbot.entity.Contact;

import java.util.List;

public interface ContactService {
    void addContact(Contact contact);

    List<Contact> getAll();
}
