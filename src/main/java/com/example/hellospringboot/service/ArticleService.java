package com.example.hellospringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hellospringboot.entity.Article;
import com.example.hellospringboot.repository.ArticleMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

/**
 * Week 08 - 文章业务层：知道 Mapper，从没见过 HTTP。
 *
 * 校验（标题与正文不能为空）住在这里，和 Week 04 的消息校验同一个家。
 */
@Service
public class ArticleService {

    private final ArticleMapper articleMapper;

    // 构造注入
    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /** publish —— 一篇文章进入世界 */
    public Article publish(Article article) {
        if (article.getTitle() == null || article.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title 不能为空");
        }
        if (article.getContent() == null || article.getContent().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "content 不能为空");
        }
        article.setId(null);
        article.setDeleted(0);
        article.setCreatedAt(LocalDateTime.now());
        articleMapper.insert(article);
        return article;
    }

    /** read one —— 只为展示的那一篇 */
    public Article getById(Long id) {
        return articleMapper.selectById(id);
    }

    /** discover —— 标题或正文命中关键词，最新优先，分页返回 */
    public IPage<Article> search(Long pageNum, Long pageSize, String keyword) {
        LambdaQueryWrapper<Article> qw = new LambdaQueryWrapper<>();
        if (keyword != null && !keyword.isBlank()) {
            qw.like(Article::getTitle, keyword)
              .or()
              .like(Article::getContent, keyword);
        }
        qw.orderByDesc(Article::getCreatedAt);
        return articleMapper.selectPage(new Page<>(pageNum, pageSize), qw);
    }

    /** revise —— 课程八周以来第一个"改变主意"的动词 */
    public Article revise(Long id, Article req) {
        Article existing = articleMapper.selectById(id);
        if (existing == null) {
            return null;
        }
        if (req.getTitle() == null || req.getTitle().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title 不能为空");
        }
        if (req.getContent() == null || req.getContent().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "content 不能为空");
        }
        existing.setTitle(req.getTitle());
        existing.setContent(req.getContent());
        articleMapper.updateById(existing);
        return existing;
    }

    /** withdraw —— 有意地收回（软删除：记忆仍在，只是不再示人） */
    public void withdraw(Long id) {
        articleMapper.deleteById(id);
    }
}
