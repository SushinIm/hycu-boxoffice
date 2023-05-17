package com.hycu.boxoffice.presenter.schedule;

import com.hycu.boxoffice.usecase.port.input.IBoxOfficeInUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class BoxOfficeScheduler {

    @Value("${api.key}")
    private String apiKey;
    private final IBoxOfficeInUseCase  boxOfficeInUseCase;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void updateProductRankingPoint() {
        boxOfficeInUseCase.addDailyBoxOffice(apiKey);
    }
}
