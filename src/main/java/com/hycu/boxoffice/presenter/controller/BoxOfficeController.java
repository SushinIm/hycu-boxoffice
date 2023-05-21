package com.hycu.boxoffice.presenter.controller;

import com.hycu.boxoffice.usecase.port.input.IBoxOfficeInUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/box-office")
public class BoxOfficeController {

    @Value("${api.key}")
    private String apiKey;
    private final IBoxOfficeInUseCase boxOfficeInUseCase;

    @GetMapping
    public void asd() {
        boxOfficeInUseCase.addDailyBoxOffice(apiKey);
    }
}
