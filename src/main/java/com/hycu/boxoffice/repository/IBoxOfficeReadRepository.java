package com.hycu.boxoffice.repository;

import com.hycu.boxoffice.domain.entity.BoxOfficeApiEntity;
import com.hycu.boxoffice.domain.entity.PeriodBoxOfficeEntity;
import com.hycu.boxoffice.domain.payload.request.BoxOfficeReq;
import java.util.List;

public interface IBoxOfficeReadRepository {

    List<BoxOfficeApiEntity> getDailyBoxOffice(String apiKey);

    List<PeriodBoxOfficeEntity> getPeriodBoxOffice(BoxOfficeReq request);
}
