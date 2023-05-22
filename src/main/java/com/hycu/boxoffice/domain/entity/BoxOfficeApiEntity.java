package com.hycu.boxoffice.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoxOfficeApiEntity {
    @JsonProperty("rnum")
    private Integer rowNum;
    @JsonProperty("rank")
    private Integer ranking;
    @JsonProperty("rankInten")
    private Long rankIntensity;
    @JsonProperty("rankOldAndNew")
    private String rankOldAndNew;
    @JsonProperty("movieCd")
    private Integer movieCode;
    @JsonProperty("movieNm")
    private String movieName;
    @JsonProperty("openDt")
    private LocalDate openedAt;
    @JsonProperty("salesAmt")
    private Long salesAmount;
    @JsonProperty("salesShare")
    private BigDecimal salesShare;
    @JsonProperty("salesInten")
    private Long salesIntensity;
    @JsonProperty("salesChange")
    private BigDecimal salesChange;
    @JsonProperty("salesAcc")
    private Long salesAccumulate;
    @JsonProperty("audiCnt")
    private Long audienceCount;
    @JsonProperty("audiInten")
    private Long audienceIntensity;
    @JsonProperty("audiChange")
    private BigDecimal audienceChange;
    @JsonProperty("audiAcc")
    private Long audienceAccumulate;
    @JsonProperty("scrnCnt")
    private Long screenCount;
    @JsonProperty("showCnt")
    private Long showCount;
}
