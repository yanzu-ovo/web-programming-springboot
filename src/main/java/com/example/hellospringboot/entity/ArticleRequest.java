package com.example.hellospringboot.entity;

/**
 * Week 08 - 发布/修改文章时前端提交的载荷。
 * 与 MessageRequest 同理：请求体是"客人带来的话"，不是实体本身。
 */
public class ArticleRequest {

    private String title;
    private String content;

    public ArticleRequest() {
    }

    public ArticleRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
