package com.example.hellospringboot.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hellospringboot.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import com.example.hellospringboot.entity.Message;

public interface MessageMapper extends BaseMapper<Message> {
    IPage<Message> searchMessage(Page<Message> page, @Param("keyword") String keyword);
}


