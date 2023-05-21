package com.hycu.boxoffice.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoxOfficeApiResponseEntity {
    @JsonProperty("boxOfficeResult")
    private BoxOfficeResultEntity boxOfficeResult;
}
