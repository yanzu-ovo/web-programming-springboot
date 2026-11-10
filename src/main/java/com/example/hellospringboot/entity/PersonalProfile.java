package com.example.hellospringboot.entity;

public class PersonalProfile {
    private String blogName;
    private String owner;
    private String introduction;
    private String interests;
    private String vision;

    // 无参构造（必须）
    public PersonalProfile(){}

    // 全参构造
    public PersonalProfile(String blogName, String owner, String introduction, String interests, String vision) {
        this.blogName = blogName;
        this.owner = owner;
        this.introduction = introduction;
        this.interests = interests;
        this.vision = vision;
    }

    // getter setter
    public String getBlogName() {
        return blogName;
    }
    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getIntroduction() {
        return introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getInterests() {
        return interests;
    }
    public void setInterests(String interests) {
        this.interests = interests;
    }
    public String getVision() {
        return vision;
    }
    public void setVision(String vision) {
        this.vision = vision;
    }
}