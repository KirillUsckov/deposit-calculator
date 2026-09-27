package ru.kduskov.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class CalculateDepositRequest {
    @NotNull
    @DecimalMin(value = "1000.0", message = "Минимальная сумма вклада 1000 ₽")
    @DecimalMax(value = "10000000.0", message = "Максимальная сумма вклада 10 000 000 ₽")
    private final BigDecimal amount;
    @NotNull
    @Min(value = 1, message = "Минимальный срок вклада 1 месяц")
    @Max(value = 60, message = "Максимальный срок вклада 60 месяцев")
    private final Integer months;
    @NotNull
    @DecimalMin(value = "1.0", message = "Минимальная ставка 1 %")
    @DecimalMax(value = "20.0", message = "Максимальная ставка 20 %")
    private final BigDecimal rate;
}
