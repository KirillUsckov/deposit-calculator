package ru.kduskov.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kduskov.dto.CalculateDepositRequest;
import ru.kduskov.dto.CalculateDepositResponse;
import ru.kduskov.services.DepositCalculatorService;

@Slf4j
@RestController
@RequestMapping("/api/")
public class DepositCalculatorController {
    @Autowired
    private DepositCalculatorService calculatorService;

    @PostMapping("calculate")
    public CalculateDepositResponse calculate(@Valid @RequestBody CalculateDepositRequest request) {
        log.info("Deposit calculation request started");
        long start = System.currentTimeMillis();

        CalculateDepositResponse response = calculatorService.calculate(request);

        log.info("Deposit calculation completed in {} ms", System.currentTimeMillis() - start);
        return response;
    }
}