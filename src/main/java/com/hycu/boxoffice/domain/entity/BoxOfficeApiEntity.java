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
    private Integer rank;
    @JsonProperty("rankInten")
    private Integer rankIntensity;
    @JsonProperty("rankOldAndNew")
    private String rankOldAndNew;
    @JsonProperty("movieCd")
    private Integer movieCode;
    @JsonProperty("movieNm")
    private String movieName;
    @JsonProperty("openDt")
    private LocalDate openedAt;
    @JsonProperty("salesAmt")
    private BigDecimal salesAmount;
    @JsonProperty("salesShare")
    private BigDecimal salesShare;
    @JsonProperty("salesInten")
    private BigDecimal salesIntensity;
    @JsonProperty("salesChange")
    private BigDecimal salesChange;
    @JsonProperty("salesAcc")
    private BigDecimal salesAccumulate;
    @JsonProperty("audiCnt")
    private Integer audienceCount;
    @JsonProperty("audiInten")
    private BigDecimal audienceIntensity;
    @JsonProperty("audiChange")
    private BigDecimal audienceChange;
    @JsonProperty("audiAcc")
    private BigDecimal audienceAccumulate;
    @JsonProperty("scrnCnt")
    private Integer screenCount;
    @JsonProperty("showCnt")
    private Integer showCount;
}
