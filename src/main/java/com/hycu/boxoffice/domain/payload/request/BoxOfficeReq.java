package com.hycu.boxoffice.domain.payload.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoxOfficeReq {
    private LocalDate startDate;
    private LocalDate endDate;
}
