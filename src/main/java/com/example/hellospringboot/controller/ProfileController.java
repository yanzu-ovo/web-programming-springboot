package com.example.hellospringboot.controller;

import com.example.hellospringboot.entity.PersonalProfile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Week 02 - I Speak（发声）：一个 Java 对象变成浏览器能读的 JSON。
 * Week 03 - I Am Someone（身份）：让声音有了主人。
 *
 * /profile 是完整画像（Week 03 的 PersonalProfile）；
 * /me 是简短介绍，两者不能互相矛盾 —— 否则 MyBlog 听起来像两个人。
 *
 * 命名即发布：Java 里的字段名就是浏览器里读到的 JSON 键。
 *
 * curl http://localhost:8080/profile
 * curl http://localhost:8080/me
 */
@RestController
public class ProfileController {

    // Week 03：完整身份档案（字段就是一种决定：留下什么，略去什么）
    @GetMapping("/profile")
    public PersonalProfile profile() {
        return new PersonalProfile(
                "MyBlog",                       // blogName     这个地方叫什么
                "yanzu",                        // owner        这个博客的主人是谁
                "I build things that matter.",  // introduction 一句话介绍
                "systems, long-form writing",   // interests    兴趣
                "Learn by building, one week at a time." // vision 愿景
        );
    }

    // Week 02：简短的一句 JSON —— 发声（保留 Week02 的能力，我们不替换，只生长）
    @GetMapping("/me")
    public Map<String, String> me() {
        Map<String, String> voice = new LinkedHashMap<>();
        voice.put("name", "MyBlog");
        voice.put("owner", "yanzu");
        voice.put("message", "Nice to meet you.");
        return voice;
    }
}
