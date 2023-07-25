package com.company.infoservice.service;


import com.company.infoservice.dto.ExchangeRateDto;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeRateService {

    /**
     * Returns a list of exchange rates
     *
     * @return List<ExchangeRateDto>
     */
    List<ExchangeRateDto> getExchangeRatesDto();

    /**
     * Updates exchange rates by api and saves them in database
     */
    void updateExchangeRates();

    /**
     * Returns an object representing buying and selling equivalent in Zloty of provided currency
      */
    ExchangeRateDto exchange(String currency, BigDecimal amount);
}
