package com.example.hellospringboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hellospringboot.entity.Message;
import com.example.hellospringboot.repository.MessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BlogProfile {

    @Autowired
    private MessageMapper messageMapper;

    // 分页+搜索
    public IPage<Message> searchMessage(Long pageNum, Long pageSize, String keyword) {
        Page<Message> page = new Page<>(pageNum, pageSize);
        return messageMapper.searchMessage(page, keyword);
    }

    // 你原来别的方法（新增、查单条）写在这里
}