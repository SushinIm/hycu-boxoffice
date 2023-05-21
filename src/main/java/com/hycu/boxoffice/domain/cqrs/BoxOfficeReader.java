package com.hycu.boxoffice.domain.cqrs;

import com.hycu.boxoffice.domain.mapper.IBoxOfficeMapper;
import com.hycu.boxoffice.domain.repository.IBoxOfficeReadRepository;
import com.hycu.boxoffice.usecase.model.BoxOfficeModel;
import com.hycu.boxoffice.usecase.port.output.IBoxOfficeReadOutUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@RequiredArgsConstructor
public class BoxOfficeReader implements IBoxOfficeReadOutUseCase {

    private final IBoxOfficeReadRepository boxOfficeReadRepository;
    private final IBoxOfficeMapper boxOfficeMapper;

    @Override
    public List<BoxOfficeModel> getDailyBoxOffice(String apiKey) {
        Assert.hasText(apiKey, "OpenApi 호출을 위한 인증키 누락");
        return boxOfficeReadRepository.getDailyBoxOffice(apiKey)
                .stream().map(boxOfficeMapper::apiEntityToModel).toList();
    }
}
