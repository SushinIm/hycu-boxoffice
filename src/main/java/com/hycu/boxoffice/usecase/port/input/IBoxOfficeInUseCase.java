package com.hycu.boxoffice.usecase.port.input;

import com.hycu.boxoffice.presenter.payload.request.BoxOfficeReq;
import com.hycu.boxoffice.usecase.model.BoxOfficeModel;
import java.util.List;

public interface IBoxOfficeInUseCase {

    void addDailyBoxOffice(String apiKey);

    List<BoxOfficeModel> getPeriodBoxOffice(BoxOfficeReq request);
}
