package com.example.chatbot.business.concrete;

import com.example.chatbot.business.abstracts.ContactService;
import com.example.chatbot.dataAccesLayer.ContactRepository;
import com.example.chatbot.dataAccesLayer.UserRepository;
import com.example.chatbot.entity.Contact;
import com.example.chatbot.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Data
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;


    @Override
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow();
        contactRepository.delete(contact);
    }

}
