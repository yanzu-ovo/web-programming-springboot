# MyBlog — Web Programming 课程项目

一个随课程六周逐步生长的 Spring Boot 博客后端：**我们只添加，从不替换**。

## 六周成长路线

| Week | 能力 | 端点 | 核心代码 |
|------|------|------|----------|
| 01 | **Hello, I Exist**（存在）— 一个能回答的服务器 | `GET /hello` → `Hello, I exist.` | `controller/HelloController.java` |
| 02 | **I Speak**（发声）— Java 对象变成浏览器能读的 JSON | `GET /me` | `controller/ProfileController.java` |
| 03 | **I Am Someone**（身份）— 声音有了主人，字段即决定 | `GET /profile` → 完整身份档案 | `entity/PersonalProfile.java` + `ProfileController` |
| 04 | **I Can Listen**（倾听）— 读是 GET，留言是 POST | `POST /messages`、`GET /messages`、`GET /messages/{id}` | `controller/MessageController.java` |
| 05 | **I Have Structure**（组织）— Controller → Service → Repository，依赖只向下 | 同上 | `service/MessageService.java`、`repository/MessageRepository.java`、`InMemoryMessageRepository` |
| 06 | **Memory Palace**（记忆宫殿）— MySQL + MyBatis Plus 持久化 | `GET /messages/search`（分页 + 搜索） | `repository/MessageMapper.java`、`DbMessageRepository`、`mapper/MessageMapper.xml` |

## 快速体验

```bash
# Week 01 — 存在
curl http://localhost:8080/hello

# Week 02/03 — 发声与身份
curl http://localhost:8080/me
curl http://localhost:8080/profile

# Week 04 — 倾听
curl -X POST http://localhost:8080/messages \
     -H "Content-Type: application/json" \
     -d '{"content":"Hello, MyBlog"}'
curl http://localhost:8080/messages
curl http://localhost:8080/messages/1

# Week 06 — 记忆宫殿（分页 + 搜索）
curl "http://localhost:8080/messages/search?page=1&size=5&keyword=Hello"
```

## 架构（Week 05 起）

```
Controller   只懂 HTTP（知道 Service，不知道 Repository 存在）
    ↓
Service      业务与校验（知道 Repository，从没见过 HTTP）
    ↓
Repository   存储接口（InMemoryMessageRepository / DbMessageRepository @Primary）
    ↓
Mapper → MySQL   Week 06：消息活在数据库里，不在会结束的进程里
```

依赖只能向下流动；一条存储路径，一个查找的地方。

## 运行前提

- JDK 17、Gradle（含 wrapper）
- MySQL 运行于 `localhost:3306`，库名 `blog_db`（账号密码见 `application.yml`）

```bash
./gradlew bootRun
```
