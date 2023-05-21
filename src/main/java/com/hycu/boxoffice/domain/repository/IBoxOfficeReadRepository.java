package com.hycu.boxoffice.domain.repository;

import com.hycu.boxoffice.domain.entity.BoxOfficeApiEntity;
import java.util.List;

public interface IBoxOfficeReadRepository {

    List<BoxOfficeApiEntity> getDailyBoxOffice(String apiKey);
}
