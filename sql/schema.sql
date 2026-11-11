-- MyBlog 数据库初始化（blog_db）
CREATE DATABASE IF NOT EXISTS blog_db DEFAULT CHARACTER SET utf8mb4;
USE blog_db;

-- Week 04-07：留言表
CREATE TABLE IF NOT EXISTS message (
    id         BIGINT   AUTO_INCREMENT PRIMARY KEY,
    content    TEXT     NOT NULL COMMENT '留言内容',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='访客留言';

-- Week 08：文章表（软删除：0 在世，1 已撤回）
-- "我发布过，然后收回了它"对数字自我来说也是真实的故事。
CREATE TABLE IF NOT EXISTS article (
    id         BIGINT       AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(200) NOT NULL COMMENT '卡片标题',
    content    TEXT         NOT NULL COMMENT '门面要预览的正文',
    created_at DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '让"最新优先"成为可能',
    deleted    TINYINT      NOT NULL DEFAULT 0 COMMENT '0 在世，1 已撤回'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT ='MyBlog 的文章';
