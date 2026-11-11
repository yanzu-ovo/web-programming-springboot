package com.example.hellospringboot.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/**
 * Week 08 - 让意外也遵守契约。
 *
 * 过去抛出的异常会变成一段没人解析得动的 HTML/堆栈 ——
 * "shapes nobody parsed"。现在无论发生什么，
 * 调用方拿到的都是同一个 Result 骨架。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public Result<Void> handleStatus(ResponseStatusException ex) {
        return Result.fail(ex.getStatusCode().value(), ex.getReason());
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleOther(Exception ex) {
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "server error: " + ex.getMessage());
    }
}
