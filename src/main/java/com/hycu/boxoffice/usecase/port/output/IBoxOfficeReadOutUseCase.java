package com.hycu.boxoffice.usecase.port.output;

import com.hycu.boxoffice.domain.payload.request.BoxOfficeReq;
import com.hycu.boxoffice.domain.model.BoxOfficeModel;
import com.hycu.boxoffice.domain.model.PeriodBoxOfficeModel;
import java.util.List;

public interface IBoxOfficeReadOutUseCase {

    List<BoxOfficeModel> getDailyBoxOffice(String apiKey);

    List<PeriodBoxOfficeModel> getPeriodBoxOffice(BoxOfficeReq request);
}
