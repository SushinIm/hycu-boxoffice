package com.hycu.boxoffice.usecase.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoxOfficeModel {
    private final Integer movieCode;
    private final String movieName;
    private final Integer ranking;
    private final Integer rankIntensity;
    private final Boolean newRanked;
    private final Long salesAmount;
    private final BigDecimal salesShare;
    private final Long salesIntensity;
    private final BigDecimal salesChange;
    private final Long salesAccumulate;
    private final Long audienceCount;
    private final Long audienceIntensity;
    private final BigDecimal audienceChange;
    private final Long audienceAccumulate;
    private final Long screenCount;
    private final Long showCount;
    private final LocalDate openedAt;
    private final LocalDate savedAt;
}