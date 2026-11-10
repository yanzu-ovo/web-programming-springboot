package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Message;
import com.example.hellospringboot.repository.MessageRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    // 构造注入
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message msg) {
        // 以后可以在这里加校验逻辑
        return messageRepository.add(msg);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getOneMessage(Long id){
        return messageRepository.findById(id);
    }
}
