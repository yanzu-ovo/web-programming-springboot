package com.example.hellospringboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.hellospringboot.common.Result;
import com.example.hellospringboot.entity.Article;
import com.example.hellospringboot.entity.ArticleRequest;
import com.example.hellospringboot.service.ArticleService;
import org.springframework.web.bind.annotation.*;

/**
 * Week 08 - I Am Complete：MyBlog 有了发布的生活（a publishing life）。
 *
 * 五个能力，五条线：
 *   publish   POST   /articles                       —— 有东西进入世界
 *   read one  GET    /articles/{id}                  —— 只为展示的那一篇
 *   discover  GET    /articles?keyword=&page=&size=  —— 一页可以浏览的目录
 *   revise    PUT    /articles/{id}                  —— 八周以来第一次改变主意
 *   withdraw  DELETE /articles/{id}                  —— 有意地收回
 *
 * 全部返回 Result 统一契约：{code, message, data}
 *
 * curl -X POST http://localhost:8080/articles -H "Content-Type: application/json" \
 *      -d '{"title":"My first article","content":"Hello, this is MyBlog speaking."}'
 * curl http://localhost:8080/articles/1
 * curl "http://localhost:8080/articles?keyword=MyBlog&page=1&size=5"
 * curl -X PUT http://localhost:8080/articles/1 -H "Content-Type: application/json" \
 *      -d '{"title":"Revised","content":"I changed my mind."}'
 * curl -X DELETE http://localhost:8080/articles/1
 */
@RestController
@RequestMapping("/articles")
@CrossOrigin
public class ArticleController {

    private final ArticleService articleService;

    // 构造注入
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 发布 POST
    @PostMapping
    public Result<Article> publish(@RequestBody ArticleRequest request) {
        Article a = new Article();
        a.setTitle(request.getTitle());
        a.setContent(request.getContent());
        return Result.ok(articleService.publish(a));
    }

    // 读取单篇 GET
    @GetMapping("/{id}")
    public Result<Article> getOne(@PathVariable Long id) {
        Article a = articleService.getById(id);
        if (a == null) {
            return Result.fail(404, "article not found");
        }
        return Result.ok(a);
    }

    // 发现 GET：关键词 + 分页
    @GetMapping
    public Result<IPage<Article>> search(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "5") Long size,
            @RequestParam(required = false) String keyword
    ) {
        return Result.ok(articleService.search(page, size, keyword));
    }

    // 修改 PUT —— 第一个 update 动词
    @PutMapping("/{id}")
    public Result<Article> revise(@PathVariable Long id, @RequestBody ArticleRequest request) {
        Article req = new Article();
        req.setTitle(request.getTitle());
        req.setContent(request.getContent());
        Article updated = articleService.revise(id, req);
        if (updated == null) {
            return Result.fail(404, "article not found");
        }
        return Result.ok(updated);
    }

    // 撤回 DELETE —— 软删除
    @DeleteMapping("/{id}")
    public Result<Void> withdraw(@PathVariable Long id) {
        articleService.withdraw(id);
        return Result.ok(null);
    }
}
