package com.sefan.training.spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class MessageController {

    private final MessageRepository repository;

    @Autowired
    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
    public Message getMessageById(@PathVariable Long id) {
        Message message = this.repository.getMessageById(id);
        return message;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public Collection<Message> getAllMessages() {
        Collection<Message> messages = this.repository.getAllMessages();
        return messages;
    }

    @RequestMapping(value = "/messages/", method = RequestMethod.POST)
    public void createMessage(String header, String content) {
        this.repository.createMessage(header, content);
    }

    @RequestMapping(value = "/messages/{id}", method = RequestMethod.DELETE)
    public void deleteMessage(@PathVariable Long id) {
        this.repository.deleteMessage(id);
    }


}
