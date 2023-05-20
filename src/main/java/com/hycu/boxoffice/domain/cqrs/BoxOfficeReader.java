package com.hycu.boxoffice.domain.cqrs;

import com.hycu.boxoffice.domain.repository.IBoxOfficeReadRepository;
import com.hycu.boxoffice.usecase.port.output.IBoxOfficeReadOutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoxOfficeReader implements IBoxOfficeReadOutUseCase {

    private final IBoxOfficeReadRepository boxOfficeReadRepository;

    @Override
    public void getDailyBoxOffice(String apiKey) {
        boxOfficeReadRepository.getDailyBoxOffice(apiKey);
    }
}
