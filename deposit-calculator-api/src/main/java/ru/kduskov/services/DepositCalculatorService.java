package ru.kduskov.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kduskov.dto.CalculateDepositRequest;
import ru.kduskov.dto.CalculateDepositResponse;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Slf4j
@Service
public class DepositCalculatorService {
    public CalculateDepositResponse calculate(
            CalculateDepositRequest request
    ) {
        log.debug("Calculating deposit");

        MathContext mc = new MathContext(
                20, RoundingMode.HALF_UP
        );

        BigDecimal monthlyRate = request.getRate()
                .divide(BigDecimal.valueOf(1200), mc);

        BigDecimal coefficient = BigDecimal.ONE
                .add(monthlyRate)
                .pow(request.getMonths(), mc);

        BigDecimal total = request.getAmount()
                .multiply(coefficient)
                .setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal profit = total
                .subtract(request.getAmount())
                .setScale(2, RoundingMode.HALF_EVEN);

        log.debug("Deposit calculation completed");
        return CalculateDepositResponse.builder()
                .total(total)
                .profit(profit)
                .build();
    }
}
