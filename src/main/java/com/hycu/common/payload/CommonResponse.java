package com.hycu.common.payload;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class CommonResponse<T> {
    private final String message;
    private final T data;

    public CommonResponse(String message, T data) {
        Assert.hasText(message, "응답 메시지가 누락되었습니다.");
        Assert.notNull(data, "응답 객체값이 누락되었습니다.");

        this.message = message;
        this.data = data;
    }
}
