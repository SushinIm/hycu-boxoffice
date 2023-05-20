package com.hycu.boxoffice.usecase.service;

import com.hycu.boxoffice.usecase.port.input.IBoxOfficeInUseCase;
import com.hycu.boxoffice.usecase.port.output.IBoxOfficeReadOutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class BoxOfficeService implements IBoxOfficeInUseCase {

    private final IBoxOfficeReadOutUseCase boxOfficeReadOutUseCase;

    @Override
    @Transactional
    public void addDailyBoxOffice(String apiKey) {
        Assert.hasText(apiKey, "OpenApi 호출을 위한 인증키 누락");
        boxOfficeReadOutUseCase.getDailyBoxOffice(apiKey);
    }
}
