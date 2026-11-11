package com.example.hellospringboot.common;

/**
 * Week 08 - 可信赖的契约（trustworthy contract）。
 *
 * 无论成功还是失败，响应体永远是同一个骨架：
 *   code     让门面决定：这次是成功还是失败
 *   message  失败时人类能读懂的话
 *   data     载荷 —— 即使为 null 也在
 *
 * 一个可预测的形状，success or failure。
 */
public class Result<T> {

    public int code;
    public String message;
    public T data;

    public Result() {
    }

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(0, "ok", data);
    }

    public static <T> Result<T> fail(int code, String message) {
        return new Result<>(code, message, null);
    }
}
