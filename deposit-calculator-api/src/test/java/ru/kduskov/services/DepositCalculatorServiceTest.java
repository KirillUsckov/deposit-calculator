package ru.kduskov.services;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.kduskov.dto.CalculateDepositRequest;

import java.math.BigDecimal;
import java.util.Random;

import static org.jeasy.random.FieldPredicates.named;
import static org.junit.jupiter.api.Assertions.*;

class DepositCalculatorServiceTest {

    private DepositCalculatorService service;
    private EasyRandom easyRandom;

    @BeforeEach
    void setUp() {
        service = new DepositCalculatorService();

        Random random = new Random(42);

        EasyRandomParameters parameters = new EasyRandomParameters()
                .seed(42L)
                .randomize(
                        named("amount"),
                        () -> BigDecimal.valueOf(
                                random.nextLong(1000, 1_000_001)
                        )
                )
                .randomize(
                        named("months"),
                        () -> random.nextInt(1, 61)
                )
                .randomize(
                        named("rate"),
                        () -> BigDecimal.valueOf(
                                random.nextInt(1, 2001),
                                2
                        )
                );

        easyRandom = new EasyRandom(parameters);
    }

    @ParameterizedTest
    @CsvSource({
            "12000, 1, 12, 12120.00, 120.00",
            "1000, 1, 1, 1000.83, 0.83"
    })
    void shouldCalculateDepositCorrectlyWithValidParams(
            String amount,
            int months,
            String rate,
            String expectedTotal,
            String expectedProfit
    ) {
        var request = new CalculateDepositRequest(
                new BigDecimal(amount),
                months,
                new BigDecimal(rate)
        );

        var response = service.calculate(request);

        assertAll(
                () -> assertEquals(
                        new BigDecimal(expectedTotal),
                        response.getTotal()
                ),
                () -> assertEquals(
                        new BigDecimal(expectedProfit),
                        response.getProfit()
                )
        );
    }

    @Test
    void shouldCalculateProfitCorrectlyWithValidParams() {
        var request = generateRequest();

        var response = service.calculate(request);

        assertEquals(
                0,
                response.getTotal()
                        .subtract(request.getAmount())
                        .compareTo(response.getProfit())
        );

    }

    @Test
    void shouldReturnTotalGreaterThanInitialAmount() {
        var request = generateRequest();

        var response = service.calculate(request);

        assertTrue(
                response.getTotal()
                        .compareTo(request.getAmount()) > 0
        );

    }

    @Test
    void shouldRoundResultsToTwoDecimalPlaces() {
        var request = generateRequest();

        var response = service.calculate(request);

        assertAll(
                () -> assertEquals(
                        2,
                        response.getTotal().scale()
                ),
                () -> assertEquals(
                        2,
                        response.getProfit().scale()
                )
        );

    }

    private CalculateDepositRequest generateRequest() {
        var data = easyRandom.nextObject(DepositTestData.class);

        return new CalculateDepositRequest(
                data.amount,
                data.months,
                data.rate
        );
    }

    public static class DepositTestData {
        public BigDecimal amount;
        public Integer months;
        public BigDecimal rate;
    }
}