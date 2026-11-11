package com.example.hellospringboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.hellospringboot.common.Result;
import com.example.hellospringboot.entity.Message;
import com.example.hellospringboot.entity.MessageRequest;
import com.example.hellospringboot.service.BlogProfile;
import com.example.hellospringboot.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Week 04 - I Can Listen（倾听）：读是 GET，留言是 POST。
 *           POST /messages 收下访客的话，GET /messages / GET /messages/{id} 读回来。
 * Week 05 - I Have Structure（组织）：Controller 只管 HTTP，
 *           业务交给 Service，数据交给 Repository —— 依赖只向下。
 * Week 06 - Memory Palace（记忆宫殿）：Repository 背后换成
 *           Mapper + MySQL（MyBatis Plus），消息在进程结束后依然存在。
 * Week 07 - It Can Be Found（被找到）：keyword + 分页，让记忆可以航行。
 * Week 08 - 契约统一：消息与文章返回同一个 Result 骨架
 *           {code, message, data} —— 这是设计，不是偷懒。
 *
 * curl -X POST http://localhost:8080/messages -H "Content-Type: application/json" -d "{\"content\":\"Hello, MyBlog\"}"
 * curl http://localhost:8080/messages
 * curl http://localhost:8080/messages/1
 * curl "http://localhost:8080/messages/search?page=1&size=5&keyword=Hello"
 */
@RestController
@RequestMapping("/messages")
@CrossOrigin
public class MessageController {

    private final MessageService messageService;
    private final BlogProfile blogProfile;

    // 构造注入
    public MessageController(MessageService messageService, BlogProfile blogProfile) {
        this.messageService = messageService;
        this.blogProfile = blogProfile;
    }

    // 新增消息 POST
    @PostMapping
    public Result<Message> addMessage(@RequestBody MessageRequest request) {
        Message m = new Message();
        m.setContent(request.getContent());
        return Result.ok(messageService.addMessage(m));
    }

    // 查询全部 GET
    @GetMapping
    public Result<List<Message>> getAll() {
        return Result.ok(messageService.getAllMessages());
    }

    // 根据id查询单条 GET
    @GetMapping("/{id}")
    public Result<Message> getOne(@PathVariable Long id) {
        Message m = messageService.getOneMessage(id);
        if (m == null) {
            return Result.fail(404, "message not found");
        }
        return Result.ok(m);
    }

    // 分页+搜索接口
    @GetMapping("/search")
    public Result<IPage<Message>> searchMessages(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "5") Long size,
            @RequestParam(required = false) String keyword
    ){
        return Result.ok(blogProfile.searchMessage(page, size, keyword));
    }

}
