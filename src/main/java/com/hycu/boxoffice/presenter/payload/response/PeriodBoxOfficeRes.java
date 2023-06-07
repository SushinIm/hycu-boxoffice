package com.hycu.boxoffice.presenter.payload.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PeriodBoxOfficeRes {
    private Integer movieCode;
    private String movieName;
    private Integer ranking;
    private Long salesAmount;
    private BigDecimal salesShare;
    private Long salesIntensity;
    private BigDecimal salesChange;
    private Long salesAccumulate;
    private Long audienceCount;
    private Long audienceIntensity;
    private BigDecimal audienceChange;
    private Long audienceAccumulate;
    private Long screenCount;
    private Long showCount;
    private LocalDate openedAt;

    public PeriodBoxOfficeRes(Integer movieCode, String movieName, Integer ranking, Long salesAmount,
            BigDecimal salesShare, Long salesIntensity, BigDecimal salesChange, Long salesAccumulate,
            Long audienceCount, Long audienceIntensity, BigDecimal audienceChange, Long audienceAccumulate,
            Long screenCount, Long showCount, LocalDate openedAt) {

        this.movieCode = movieCode;
        this.movieName = movieName;
        this.ranking = ranking;
        this.salesAmount = salesAmount;
        this.salesShare = salesShare;
        this.salesIntensity = salesIntensity;
        this.salesChange = salesChange;
        this.salesAccumulate = salesAccumulate;
        this.audienceCount = audienceCount;
        this.audienceIntensity = audienceIntensity;
        this.audienceChange = audienceChange;
        this.audienceAccumulate = audienceAccumulate;
        this.screenCount = screenCount;
        this.showCount = showCount;
        this.openedAt = openedAt;
    }

}
