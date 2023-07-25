package com.company.infoservice.controller;

;
import com.company.infoservice.dto.ExchangeRateDto;
import com.company.infoservice.service.ExchangeRateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/exchange-rates")
@RequiredArgsConstructor
@Validated
@Tag(name = "Exchange rates controller")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    @GetMapping
    @Operation(summary = "Get all exchange rates", description = "Returns list of the exchange rates")
    @ApiResponse(responseCode = "200", description = "Exchange rates list")
    public ResponseEntity<List<ExchangeRateDto>> getExchangeRates() {
        return ResponseEntity.ok(exchangeRateService.getExchangeRatesDto());
    }

    @GetMapping("/{currency}")
    @Operation(summary = "Get exchange rate for the currency",
            description = """
                    Returns exchange rate for requested currency and amount (or 1 if no amount specified)
                    """)
    public ExchangeRateDto exchangeCurrency(@PathVariable(value = "currency") String currency,
                                            @RequestParam(name = "amount", defaultValue = "1") BigDecimal amount) {
        return exchangeRateService.exchange(currency, amount);
    }
}