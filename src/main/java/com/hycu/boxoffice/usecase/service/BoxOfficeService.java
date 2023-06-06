package com.hycu.boxoffice.usecase.service;

import com.hycu.boxoffice.presenter.payload.request.BoxOfficeReq;
import com.hycu.boxoffice.usecase.model.BoxOfficeModel;
import com.hycu.boxoffice.usecase.port.input.IBoxOfficeInUseCase;
import com.hycu.boxoffice.usecase.port.output.IBoxOfficeReadOutUseCase;
import com.hycu.boxoffice.usecase.port.output.IBoxOfficeWriteOutUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoxOfficeService implements IBoxOfficeInUseCase {

    private final IBoxOfficeReadOutUseCase boxOfficeReadOutUseCase;
    private final IBoxOfficeWriteOutUseCase boxOfficeWriteOutUseCase;

    @Override
    @Transactional
    public void addDailyBoxOffice(String apiKey) {
        Assert.hasText(apiKey, "OpenApi 호출을 위한 인증키 누락");
        List<BoxOfficeModel> boxOfficeList =  boxOfficeReadOutUseCase.getDailyBoxOffice(apiKey);
        boxOfficeWriteOutUseCase.saveBoxOfficeList(boxOfficeList);
    }

    @Override
    public List<BoxOfficeModel> getPeriodBoxOffice(BoxOfficeReq request) {
        Assert.notNull(request, "검색 데이터 누락");
        Assert.notNull(request.getStartDate(), "시작 기준일 누락");
        Assert.notNull(request.getEndDate(), "종료 기준일 누락");

        return boxOfficeReadOutUseCase.getPeriodBoxOffice(request);
    }
}
