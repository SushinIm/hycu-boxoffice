package com.hycu.boxoffice.repository;

import com.hycu.boxoffice.domain.entity.BoxOfficeEntity;
import java.util.List;

public interface IBoxOfficeWriteRepository {

    void saveBoxOfficeList(List<BoxOfficeEntity> boxOfficeList);
}
