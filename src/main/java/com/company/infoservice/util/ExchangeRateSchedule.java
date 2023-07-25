package com.company.infoservice.util;

import com.company.infoservice.exception.ExchangeRateServiceException;
import com.company.infoservice.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExchangeRateSchedule {

    private final ExchangeRateService exchangeRateService;

    @Scheduled(cron = "${schedule.cron.updateExchangeRates}", zone = "Europe/Paris")
    public void updateExchangeRate() {
        try {
            exchangeRateService.updateExchangeRates();
        } catch (ExchangeRateServiceException ex) {
            log.error("Update current exchange rates by api failed");
        }
    }

}
