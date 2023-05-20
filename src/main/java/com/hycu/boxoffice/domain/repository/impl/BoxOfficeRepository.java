package com.hycu.boxoffice.domain.repository.impl;

import com.hycu.boxoffice.domain.repository.IBoxOfficeReadRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
@RequiredArgsConstructor
public class BoxOfficeRepository implements IBoxOfficeReadRepository {

    private final WebClient webClient;

    @Override
    public void getDailyBoxOffice(String apiKey) {
        Assert.hasText(apiKey, "OpenApi 호출을 위한 인증키 누락");
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        Object result = webClient.get()
                .uri(getDailyBoxOfficeApiUrl(apiKey, today))
                .retrieve()
                .bodyToMono(Object.class)//TODO DTO 로 변경
                .block();
    }

    private String getDailyBoxOfficeApiUrl(String apiKey, String today) {
        return String.format("/boxoffice/searchDailyBoxOfficeList.json?key=%s&targetDt=%s", apiKey, today);
    }
}
