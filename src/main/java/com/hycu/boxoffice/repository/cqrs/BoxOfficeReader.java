package com.hycu.boxoffice.repository.cqrs;

import com.hycu.boxoffice.domain.mapper.IBoxOfficeMapper;
import com.hycu.boxoffice.repository.IBoxOfficeReadRepository;
import com.hycu.boxoffice.domain.payload.request.BoxOfficeReq;
import com.hycu.boxoffice.domain.model.BoxOfficeModel;
import com.hycu.boxoffice.domain.model.PeriodBoxOfficeModel;
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

    @Override
    public List<PeriodBoxOfficeModel> getPeriodBoxOffice(BoxOfficeReq request) {
        Assert.notNull(request, "검색 데이터 누락");
        Assert.notNull(request.getStartDate(), "시작 기준일 누락");
        Assert.notNull(request.getEndDate(), "종료 기준일 누락");
        return boxOfficeReadRepository.getPeriodBoxOffice(request).stream()
                .map(boxOfficeMapper::toPeriodBoxOfficeModel).toList();
    }
}
