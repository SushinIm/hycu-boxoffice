package com.hycu.boxoffice.usecase.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoxOfficeModel {
    private final Integer rowNum;
    private final Integer rank;
    private final Integer rankIntensity;
    private final Boolean newRanked;
    private final Integer movieCode;
    private final String movieName;
    private final LocalDate openedAt;
    private final BigDecimal salesAmount;
    private final BigDecimal salesShare;
    private final BigDecimal salesIntensity;
    private final BigDecimal salesChange;
    private final BigDecimal salesAccumulate;
    private final Integer audienceCount;
    private final BigDecimal audienceIntensity;
    private final BigDecimal audienceChange;
    private final BigDecimal audienceAccumulate;
    private final Integer screenCount;
    private final Integer showCount;
    private final LocalDate savedAt;
}
