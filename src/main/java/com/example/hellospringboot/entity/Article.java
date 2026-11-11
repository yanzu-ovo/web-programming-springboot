package com.example.hellospringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Week 08 - Article：可以发布的自己。
 *
 * 最小诚实形状（minimum honest shape）——每个字段都必须为将来的门面服务：
 *   id         是谁
 *   title      卡片的标题
 *   content    门面要预览的正文
 *   createdAt  让"最新优先"成为可能
 *   deleted    软删除：0 在世，1 已撤回（"我发布过，然后收回了它"也是真实的故事）
 */
@Data
@TableName("article")
public class Article {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    @TableLogic // 逻辑删除：deleteById 会变成 UPDATE deleted=1，查询自动带上 deleted=0
    private Integer deleted;
}
