package com.example.hellospringboot.repository;

import com.example.hellospringboot.entity.Message;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryMessageRepository implements MessageRepository {

    private final List<Message> messageList = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public Message add(Message msg) {
        msg.setId(nextId++);
        messageList.add(msg);
        return msg;
    }

    @Override
    public List<Message> findAll() {
        return messageList;
    }

    @Override
    public Message findById(Long id) {
        for (Message m : messageList) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }
}
