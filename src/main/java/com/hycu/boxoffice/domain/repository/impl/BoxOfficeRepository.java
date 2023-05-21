package com.hycu.boxoffice.domain.repository.impl;

import com.hycu.boxoffice.domain.entity.BoxOfficeApiEntity;
import com.hycu.boxoffice.domain.entity.BoxOfficeApiResponseEntity;
import com.hycu.boxoffice.domain.repository.IBoxOfficeReadRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
@RequiredArgsConstructor
public class BoxOfficeRepository implements IBoxOfficeReadRepository {

    private final WebClient webClient;

    @Override
    public List<BoxOfficeApiEntity> getDailyBoxOffice(String apiKey) {
        Assert.hasText(apiKey, "OpenApi 호출을 위한 인증키 누락");
        String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        BoxOfficeApiResponseEntity response = webClient.get()
                .uri(getDailyBoxOfficeApiUrl(apiKey, yesterday))
                .retrieve()
                .bodyToMono(BoxOfficeApiResponseEntity.class)
                .block();

        Assert.notNull(response, "응답값 부재");
        Assert.notNull(response.getBoxOfficeResult(), "응답값 부재");
        Assert.notNull(response.getBoxOfficeResult().getBoxOfficeList(), "응답값 부재");

        return response.getBoxOfficeResult().getBoxOfficeList();
    }

    private String getDailyBoxOfficeApiUrl(String apiKey, String date) {
        return String.format("/boxoffice/searchDailyBoxOfficeList.json?key=%s&targetDt=%s", apiKey, date);
    }
}
