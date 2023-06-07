package com.hycu.boxoffice.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PeriodBoxOfficeEntity {
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
}
