package com.company.infoservice.util;

import com.company.infoservice.dto.CurrentExchangeRateDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "exchangeRate", url = "${exchange-rate.url}")
public interface ExchangeRateFeignClient {

    @Headers("Content-Type: application/json")
    @GetMapping
    ResponseEntity<CurrentExchangeRateDto> getExchangeRatesByApi(@RequestHeader("apikey") String apiKey);
}
