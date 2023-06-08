package com.hycu.boxoffice.presenter.payload.response;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PeriodBoxOfficeRes {
    private Integer movieCode;
    private String movieName;
    private Integer ranking;
    private Long salesAccumulate;
    private Long audienceAccumulate;
    private Long screenCount;
    private Long showCount;
    private LocalDate openedAt;

    public PeriodBoxOfficeRes(Integer movieCode, String movieName, Integer ranking, Long salesAccumulate,
            Long audienceAccumulate, Long screenCount, Long showCount, LocalDate openedAt) {

        this.movieCode = movieCode;
        this.movieName = movieName;
        this.ranking = ranking;
        this.salesAccumulate = salesAccumulate;
        this.audienceAccumulate = audienceAccumulate;
        this.screenCount = screenCount;
        this.showCount = showCount;
        this.openedAt = openedAt;
    }

}
