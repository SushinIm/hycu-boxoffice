package com.hycu.boxoffice.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoxOfficeResultEntity {
    @JsonProperty("boxofficeType")
    private String boxOfficeType;
    @JsonProperty("showRange")
    private String showRange;
    @JsonProperty("dailyBoxOfficeList")
    private List<BoxOfficeApiEntity> boxOfficeList;
}
