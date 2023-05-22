package com.hycu.boxoffice.domain.cqrs;

import com.hycu.boxoffice.domain.mapper.IBoxOfficeMapper;
import com.hycu.boxoffice.domain.repository.IBoxOfficeWriteRepository;
import com.hycu.boxoffice.usecase.model.BoxOfficeModel;
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
    public List<BoxOfficeModel> saveBoxOfficeList(List<BoxOfficeModel> boxOfficeList) {
        return boxOfficeWriteRepository.saveBoxOfficeList(boxOfficeList
                        .stream().map(boxOfficeMapper::toEntity).toList())
                .stream().map(boxOfficeMapper::toModel).toList();
    }
}
