package com.hycu.boxoffice.domain.repository.impl;

import com.hycu.boxoffice.domain.entity.BoxOfficeApiEntity;
import com.hycu.boxoffice.domain.entity.BoxOfficeApiResponseEntity;
import com.hycu.boxoffice.domain.entity.BoxOfficeEntity;
import com.hycu.boxoffice.domain.repository.IBoxOfficeReadRepository;
import com.hycu.boxoffice.domain.repository.IBoxOfficeWriteRepository;
import com.hycu.boxoffice.presenter.payload.request.BoxOfficeReq;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jooq.dsl.tables.BoxOffice;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
@RequiredArgsConstructor
public class BoxOfficeRepository implements IBoxOfficeReadRepository, IBoxOfficeWriteRepository {

    private final WebClient webClient;
    private final IBoxOfficeWriterRepository boxOfficeWriterRepository;
    private final DSLContext dslContext;
    private final BoxOffice boxOffice = BoxOffice.BOX_OFFICE;

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

    @Override
    public List<BoxOfficeEntity> getPeriodBoxOffice(BoxOfficeReq request) {
        Assert.notNull(request, "검색 데이터 누락");
        Assert.notNull(request.getStartDate(), "시작 기준일 누락");
        Assert.notNull(request.getEndDate(), "종료 기준일 누락");
        return dslContext.select(boxOffice.fields())
                .from(boxOffice)
                .where(boxOffice.SAVED_AT.between(request.getStartDate(), request.getEndDate()))
                .fetchInto(BoxOfficeEntity.class);
    }

    @Override
    public List<BoxOfficeEntity> saveBoxOfficeList(List<BoxOfficeEntity> boxOfficeList) {
        Assert.notNull(boxOfficeList, "박스 오피스 정보 부재");
        return boxOfficeWriterRepository.saveAll(boxOfficeList);
    }

    private String getDailyBoxOfficeApiUrl(String apiKey, String date) {
        return String.format("/boxoffice/searchDailyBoxOfficeList.json?key=%s&targetDt=%s", apiKey, date);
    }
}
