package com.hycu.boxoffice.domain.repository;

import com.hycu.boxoffice.domain.entity.BoxOfficeApiEntity;
import com.hycu.boxoffice.domain.entity.BoxOfficeEntity;
import com.hycu.boxoffice.presenter.payload.request.BoxOfficeReq;
import java.util.List;

public interface IBoxOfficeReadRepository {

    List<BoxOfficeApiEntity> getDailyBoxOffice(String apiKey);

    List<BoxOfficeEntity> getPeriodBoxOffice(BoxOfficeReq request);
}
