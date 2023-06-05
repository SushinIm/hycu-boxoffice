package com.hycu.boxoffice.usecase.service;

import com.hycu.boxoffice.presenter.payload.request.BoxOfficeReq;
import com.hycu.boxoffice.usecase.model.BoxOfficeModel;
import com.hycu.boxoffice.usecase.port.input.IBoxOfficeInUseCase;
import com.hycu.boxoffice.usecase.port.output.IBoxOfficeReadOutUseCase;
import com.hycu.boxoffice.usecase.port.output.IBoxOfficeWriteOutUseCase;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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

        List<BoxOfficeModel> periodBoxOffcieList = new ArrayList<>();

        Map<Integer, List<BoxOfficeModel>> periodBoxOfficeMap = boxOfficeReadOutUseCase.getPeriodBoxOffice(request)
                .stream().collect(Collectors.groupingBy(BoxOfficeModel::getMovieCode));

        periodBoxOfficeMap.keySet().forEach(code -> {
            List<BoxOfficeModel> boxOfficeListByMovieCode = periodBoxOfficeMap.get(code);

            Integer movieCode = boxOfficeListByMovieCode.get(0).getMovieCode();
            String movieName = boxOfficeListByMovieCode.get(0).getMovieName();
            Integer totalRanking = 0;
            Integer totalRankIntensity = 0;
            Long totalSalesAmount = 0L;
            BigDecimal totalSalesShare = BigDecimal.ZERO;
            Long totalSalesIntensity = 0L;
            BigDecimal totalSalesChange = BigDecimal.ZERO;
            Long totalSalesAccumulate = 0L;
            Long totalAudienceCount = 0L;
            Long totalAudienceIntensity = 0L;
            BigDecimal totalAudienceChange = BigDecimal.ZERO;
            Long totalAudienceAccumulate = 0L;
            Long totalScreenCount = 0L;
            Long totalShowCount = 0L;
            LocalDate openAt = boxOfficeListByMovieCode.get(0).getOpenedAt();

            for(BoxOfficeModel boxOfficeModel : boxOfficeListByMovieCode) {
                totalRanking += boxOfficeModel.getRanking();
                totalRankIntensity += boxOfficeModel.getRankIntensity();
                totalSalesAmount += boxOfficeModel.getSalesAmount();
                totalSalesShare = totalSalesShare.add(boxOfficeModel.getSalesShare());
                totalSalesIntensity += boxOfficeModel.getSalesIntensity();
                totalSalesChange = totalSalesChange.add(boxOfficeModel.getAudienceChange());
                totalSalesAccumulate += boxOfficeModel.getSalesAccumulate();
                totalAudienceCount += boxOfficeModel.getAudienceCount();
                totalAudienceIntensity += boxOfficeModel.getAudienceIntensity();
                totalAudienceChange = totalAudienceChange.add(boxOfficeModel.getAudienceChange());
                totalAudienceAccumulate += boxOfficeModel.getAudienceAccumulate();
                totalScreenCount += boxOfficeModel.getScreenCount();
                totalShowCount += boxOfficeModel.getShowCount();
            }

            //TODO Query의 결과를 BoxOfficeModel 말고 다른 형태로 반환(최고 순위 등 반환하도록)
            periodBoxOffcieList.add(BoxOfficeModel.builder()
                    .id(null)
                    .movieCode(movieCode)
                    .movieName(movieName)
                    .newRanked(null)
                    .ranking(totalRanking / boxOfficeListByMovieCode.size())
                    .rankIntensity(totalRankIntensity / boxOfficeListByMovieCode.size())
                    .salesAmount(totalSalesAmount / boxOfficeListByMovieCode.size())
                    .salesShare(totalSalesShare.divide(
                            BigDecimal.valueOf(boxOfficeListByMovieCode.size()), 2, RoundingMode.HALF_UP))
                    .salesIntensity(totalSalesIntensity / boxOfficeListByMovieCode.size())
                    .salesChange(totalSalesChange.divide(
                            BigDecimal.valueOf(boxOfficeListByMovieCode.size()), 2, RoundingMode.HALF_UP))
                    .salesAccumulate(totalSalesAccumulate / boxOfficeListByMovieCode.size())
                    .audienceCount(totalAudienceCount / boxOfficeListByMovieCode.size())
                    .audienceIntensity(totalAudienceIntensity / boxOfficeListByMovieCode.size())
                    .audienceChange(totalAudienceChange.divide(
                            BigDecimal.valueOf(boxOfficeListByMovieCode.size()), 2, RoundingMode.HALF_UP))
                    .audienceAccumulate(totalAudienceAccumulate / boxOfficeListByMovieCode.size())
                    .screenCount(totalScreenCount / boxOfficeListByMovieCode.size())
                    .showCount(totalShowCount / boxOfficeListByMovieCode.size())
                    .openedAt(openAt)
                    .savedAt(null)
                    .build());
        });

        return periodBoxOffcieList;
    }
}
