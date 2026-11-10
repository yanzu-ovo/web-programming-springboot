package com.example.hellospringboot.repository;

import com.example.hellospringboot.entity.Message;
import java.util.List;

public interface MessageRepository {
    Message add(Message message);
    List<Message> findAll();
    Message findById(Long id);
}
