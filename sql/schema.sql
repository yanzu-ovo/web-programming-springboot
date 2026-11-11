-- Week 08：文章表（MySQL，库：blog_db）
-- 软删除设计：deleted = 0 在世，1 已撤回；
-- "我发布过，然后收回了它"对数字自我来说也是真实的故事。
CREATE TABLE IF NOT EXISTS article (
    id         BIGINT       AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(200) NOT NULL COMMENT '卡片标题',
    content    TEXT         NOT NULL COMMENT '门面要预览的正文',
    created_at DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '让"最新优先"成为可能',
    deleted    TINYINT      NOT NULL DEFAULT 0 COMMENT '0 在世，1 已撤回（软删除）'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='MyBlog 的文章';
