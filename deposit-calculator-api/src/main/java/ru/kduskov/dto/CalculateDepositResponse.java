package ru.kduskov.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
@Builder
public class CalculateDepositResponse {
    private final BigDecimal total;
    private final BigDecimal profit;
}
