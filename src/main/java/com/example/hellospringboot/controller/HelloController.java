package com.example.hellospringboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.hellospringboot.entity.Message;
import com.example.hellospringboot.entity.MessageRequest;
import com.example.hellospringboot.service.BlogProfile;
import com.example.hellospringboot.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@CrossOrigin
public class HelloController {

    private final MessageService messageService;
    private final BlogProfile blogProfile;

    // 构造注入
    public HelloController(MessageService messageService, BlogProfile blogProfile) {
        this.messageService = messageService;
        this.blogProfile = blogProfile;
    }

    // 新增消息 POST
    @PostMapping
    public Message addMessage(@RequestBody MessageRequest request) {
        Message m = new Message();
        m.setContent(request.getContent());
        return messageService.addMessage(m);
    }

    // 查询全部 GET
    @GetMapping
    public List<Message> getAll() {
        return messageService.getAllMessages();
    }

    // 根据id查询单条 GET
    @GetMapping("/{id}")
    public Message getOne(@PathVariable Long id) {
        return messageService.getOneMessage(id);
    }

    // 分页+搜索接口
    @GetMapping("/search")
    public IPage<Message> searchMessages(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "5") Long size,
            @RequestParam(required = false) String keyword
    ){
        return blogProfile.searchMessage(page, size, keyword);
    }

}