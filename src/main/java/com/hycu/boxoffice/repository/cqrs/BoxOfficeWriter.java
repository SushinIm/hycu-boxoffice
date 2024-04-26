package com.hycu.boxoffice.repository.cqrs;

import com.hycu.boxoffice.domain.mapper.IBoxOfficeMapper;
import com.hycu.boxoffice.repository.IBoxOfficeWriteRepository;
import com.hycu.boxoffice.domain.model.BoxOfficeModel;
import com.hycu.boxoffice.usecase.port.output.IBoxOfficeWriteOutUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoxOfficeWriter implements IBoxOfficeWriteOutUseCase {

    private final IBoxOfficeWriteRepository boxOfficeWriteRepository;
    private final IBoxOfficeMapper boxOfficeMapper;

    @Override
    public void saveBoxOfficeList(List<BoxOfficeModel> boxOfficeList) {
        boxOfficeWriteRepository.saveBoxOfficeList(boxOfficeList.stream().map(boxOfficeMapper::toEntity).toList());
    }
}
