package com.example.hellospringboot.service;

import com.example.hellospringboot.entity.Message;
import com.example.hellospringboot.repository.MessageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

/**
 * Week 05：业务层 —— 知道 Repository，但从没见过 HTTP。
 * Week 06：存储路径只有一条（MySQL），不与内存 List 并存。
 */
@Service
public class MessageService {
    private final MessageRepository messageRepository;

    // 构造注入
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message msg) {
        // Week 04 的校验：一句话的留言不能是空的
        if (msg == null || msg.getContent() == null || msg.getContent().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "content 不能为空");
        }
        return messageRepository.add(msg);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getOneMessage(Long id){
        return messageRepository.findById(id);
    }
}
