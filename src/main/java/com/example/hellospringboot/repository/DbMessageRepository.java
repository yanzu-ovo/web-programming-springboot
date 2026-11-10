package com.example.hellospringboot.repository;

import com.example.hellospringboot.entity.Message;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Component
@Primary // 告诉Spring优先使用这个数据库版本，不用内存List版本
public class DbMessageRepository implements MessageRepository {

    private final MessageMapper messageMapper;

    // 构造器注入mapper
    public DbMessageRepository(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public Message add(Message msg) {
        // 设置当前时间
        msg.setCreatedAt(java.time.LocalDateTime.now());
        messageMapper.insert(msg);
        return msg;
    }

    @Override
    public List<Message> findAll() {
        return messageMapper.selectList(null);
    }

    @Override
    public Message findById(Long id) {
        return messageMapper.selectById(id);
    }
}
