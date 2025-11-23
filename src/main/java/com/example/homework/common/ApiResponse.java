package com.example.homework.common;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final int status;
    private final T data;
    private final long count;

    private ApiResponse(int status, T data, long count) {
        this.status = status;
        this.data = data;
        this.count = count;
    }

    // 생성(201)
    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(201, data, 1);
    }

    // OK(200) + 단건
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(200, data, 1);
    }

    // OK(200) + 리스트/페이지
    public static <T> ApiResponse<T> ok(T data, long count) {
        return new ApiResponse<>(200, data, count);
    }
}
