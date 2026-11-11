package com.example.hellospringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Week 01 - Hello, I Exist（存在）
 *
 * MyBlog 的第一个能力：一个能回答的服务器。
 * 只有一个字符串，没有结构，但它是真实的。
 * curl http://localhost:8080/hello
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, I exist.";
    }
}
