package com.hycu.boxoffice.presenter.controller;

import com.hycu.boxoffice.presenter.mapper.IBoxOfficeModelMapper;
import com.hycu.boxoffice.presenter.payload.request.BoxOfficeReq;
import com.hycu.boxoffice.presenter.payload.response.BoxOfficeRes;
import com.hycu.boxoffice.usecase.port.input.IBoxOfficeInUseCase;
import com.hycu.common.payload.CommonList;
import com.hycu.common.payload.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/box-office")
public class BoxOfficeController {

    @Value("${api.key}")
    private String apiKey;
    private final IBoxOfficeInUseCase boxOfficeInUseCase;
    private final IBoxOfficeModelMapper boxOfficeModelMapper;

    @GetMapping
    public ResponseEntity<CommonResponse<CommonList<BoxOfficeRes>>> getPeriodBoxOffice(BoxOfficeReq request) {
        CommonList<BoxOfficeRes> boxOfficeList = new CommonList<>(boxOfficeInUseCase.getPeriodBoxOffice(request)
                .stream().map(boxOfficeModelMapper::toRes).toList());

        return new ResponseEntity<>(new CommonResponse<>(String.format("%s ~ %s 박스오피스 성적",
                request.getStartDate(), request.getEndDate()), boxOfficeList), HttpStatus.OK);
    }

    @PostMapping
    public void asd() {
        boxOfficeInUseCase.addDailyBoxOffice(apiKey);
    }
}
