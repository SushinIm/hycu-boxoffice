package com.hycu.common.payload;

import java.util.List;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class CommonList<T> {
    private final List<T> list;

    public CommonList(List<T> list) {
        Assert.notNull(list, "Api 응답 List 값을 입력하세요.");
        this.list = list;
    }
}
