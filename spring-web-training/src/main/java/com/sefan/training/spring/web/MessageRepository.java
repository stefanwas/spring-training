package com.sefan.training.spring.web;


import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class MessageRepository {

    private final IdProvider idProvider;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private final Map<Long, Message> messages = new LinkedHashMap<>();

    public MessageRepository(IdProvider idProvider) {
        this.idProvider = idProvider;
    }

    public Message getMessageById(Long id) {
        return messages.get(id);
    }

    public Collection<Message> getAllMessages() {
        return messages.values();
    }

    public void createMessage(String header, String content) {
        Long id = idProvider.getNextValue();
        String now = LocalDateTime.now().format(dateTimeFormatter);

        Message message = new Message(id, header, content, now);
        messages.put(id, message);
    }

    public void deleteMessage(Long id) {
        messages.remove(id);
    }
}
