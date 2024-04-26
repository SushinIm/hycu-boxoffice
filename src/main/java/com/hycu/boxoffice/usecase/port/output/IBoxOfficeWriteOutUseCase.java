package com.hycu.boxoffice.usecase.port.output;

import com.hycu.boxoffice.domain.model.BoxOfficeModel;
import java.util.List;

public interface IBoxOfficeWriteOutUseCase {

    void saveBoxOfficeList(List<BoxOfficeModel> boxOfficeList);
}
