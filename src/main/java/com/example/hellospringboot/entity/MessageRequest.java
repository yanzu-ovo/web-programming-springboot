package com.example.hellospringboot.entity;

public class MessageRequest {
    private Integer id;
    private String content;

    public MessageRequest() {
    }

    public MessageRequest(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}