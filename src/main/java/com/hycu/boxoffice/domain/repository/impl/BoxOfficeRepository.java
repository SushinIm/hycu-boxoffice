package com.hycu.boxoffice.domain.repository.impl;

import static org.jooq.impl.DSL.avg;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.max;
import static org.jooq.impl.DSL.min;
import static org.jooq.impl.DSL.sum;

import com.hycu.boxoffice.domain.entity.BoxOfficeApiEntity;
import com.hycu.boxoffice.domain.entity.BoxOfficeApiResponseEntity;
import com.hycu.boxoffice.domain.entity.BoxOfficeEntity;
import com.hycu.boxoffice.domain.entity.PeriodBoxOfficeEntity;
import com.hycu.boxoffice.domain.repository.IBoxOfficeReadRepository;
import com.hycu.boxoffice.domain.repository.IBoxOfficeWriteRepository;
import com.hycu.boxoffice.presenter.payload.request.BoxOfficeReq;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import jooq.dsl.tables.BoxOffice;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
@RequiredArgsConstructor
public class BoxOfficeRepository implements IBoxOfficeReadRepository, IBoxOfficeWriteRepository {

    private final WebClient webClient;
    private final DSLContext dslContext;
    private final NamedParameterJdbcTemplate jdbcTemplate;
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
    public List<PeriodBoxOfficeEntity> getPeriodBoxOffice(BoxOfficeReq request) {
        Assert.notNull(request, "검색 데이터 누락");
        Assert.notNull(request.getStartDate(), "시작 기준일 누락");
        Assert.notNull(request.getEndDate(), "종료 기준일 누락");
        return dslContext.select(boxOffice.MOVIE_CODE,
                        boxOffice.MOVIE_NAME,
                        avg(boxOffice.SALES_AMOUNT).as("sales_amount"),
                        avg(boxOffice.SALES_SHARE).as("sales_share"),
                        avg(boxOffice.SALES_INTENSITY).as("sales_intensity"),
                        avg(boxOffice.SALES_CHANGE).as("sales_change"),
                        max(boxOffice.SALES_ACCUMULATE).as("sales_accumulate"),
                        avg(boxOffice.AUDIENCE_COUNT).as("audience_count"),
                        avg(boxOffice.AUDIENCE_INTENSITY).as("audience_intensity"),
                        avg(boxOffice.AUDIENCE_CHANGE).as("audience_change"),
                        max(boxOffice.AUDIENCE_ACCUMULATE).as("audience_accumulate"),
                        sum(boxOffice.SCREEN_COUNT).as("screen_count"),
                        sum(boxOffice.SHOW_COUNT).as("show_count"),
                        min(boxOffice.OPENED_AT).as("opened_at"))
                .from(boxOffice)
                .where(boxOffice.SAVED_AT.between(request.getStartDate(), request.getEndDate()))
                .groupBy(boxOffice.MOVIE_CODE, boxOffice.MOVIE_NAME)
                .orderBy(field("audience_accumulate").desc())
                .fetchInto(PeriodBoxOfficeEntity.class);
    }

    @Override
    public void saveBoxOfficeList(List<BoxOfficeEntity> boxOfficeList) {
        Assert.notNull(boxOfficeList, "박스 오피스 정보 부재");
        String insertSql = "INSERT INTO box_office "
                + "(movie_code, movie_name, ranking, rank_intensity, new_ranked, sales_amount, sales_share, "
                + "sales_intensity, sales_change, sales_accumulate, audience_count, audience_intensity, "
                + "audience_change, audience_accumulate, screen_count, show_count, opened_at, saved_at) "
                + "VALUES (:movieCode, :movieName, :ranking, :rankIntensity, :newRanked, :salesAmount, :salesShare, "
                + ":salesIntensity, :salesChange, :salesAccumulate, :audienceCount, :audienceIntensity, "
                + ":audienceChange, :audienceAccumulate, :screenCount, :showCount, :openedAt, :savedAt) "
                + "ON DUPLICATE KEY UPDATE "
                + "movie_code = :movieCode, movie_name = :movieName, ranking = :ranking, "
                + "rank_intensity = :rankIntensity, new_ranked = :newRanked, sales_amount = :salesAmount, "
                + "sales_share = :salesShare, sales_intensity = :salesIntensity, sales_change = :salesChange, "
                + "sales_accumulate = :salesAccumulate, audience_count = :audienceCount, "
                + "audience_intensity = :audienceIntensity, audience_change = :audienceChange, "
                + "audience_accumulate = :audienceAccumulate, screen_count = :screenCount, show_count = :showCount, "
                + "opened_at = :openedAt, saved_at = :savedAt";
        jdbcTemplate.batchUpdate(insertSql, SqlParameterSourceUtils.createBatch(boxOfficeList));
    }

    private String getDailyBoxOfficeApiUrl(String apiKey, String date) {
        return String.format("/boxoffice/searchDailyBoxOfficeList.json?key=%s&targetDt=%s", apiKey, date);
    }
}
