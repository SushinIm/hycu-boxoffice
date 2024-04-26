package com.hycu.boxoffice.usecase.port.input;

import com.hycu.boxoffice.domain.payload.request.BoxOfficeReq;
import com.hycu.boxoffice.domain.model.PeriodBoxOfficeModel;
import java.util.List;

public interface IBoxOfficeInUseCase {

    void addDailyBoxOffice(String apiKey);

    List<PeriodBoxOfficeModel> getPeriodBoxOffice(BoxOfficeReq request);
}
