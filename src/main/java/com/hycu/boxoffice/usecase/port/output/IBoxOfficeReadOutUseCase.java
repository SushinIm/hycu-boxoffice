package com.hycu.boxoffice.usecase.port.output;

import com.hycu.boxoffice.usecase.model.BoxOfficeModel;
import java.util.List;

public interface IBoxOfficeReadOutUseCase {

    List<BoxOfficeModel> getDailyBoxOffice(String apiKey);
}
