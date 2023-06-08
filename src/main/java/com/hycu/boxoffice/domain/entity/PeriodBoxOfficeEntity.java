package com.hycu.boxoffice.domain.entity;

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
    private Long salesAccumulate;
    private Long audienceAccumulate;
    private Long screenCount;
    private Long showCount;
    private LocalDate openedAt;
}
