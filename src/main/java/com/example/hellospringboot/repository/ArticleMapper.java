package com.example.hellospringboot.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.hellospringboot.entity.Article;

/**
 * Week 08 - 文章的 Mapper。
 * Week 07 在消息上练出的肌肉（selectPage + like），现在承载文章。
 */
public interface ArticleMapper extends BaseMapper<Article> {
}
