package com.company.infoservice.util;


import com.company.infoservice.dto.CurrentExchangeRateDto;
import com.company.infoservice.dto.ExchangeRateDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.company.infoservice.util.TestConstants.*;


public class DtoCreator {

    public static CurrentExchangeRateDto getCurrentExchangeRateDto() {
        return new CurrentExchangeRateDto();
    }

    public static ExchangeRateDto getExchangeRateDto() {
        return new ExchangeRateDto(
                UUID,
                COUNTRY,
                CURRENCY_NAME,
                CURRENCY_CODE,
                AMOUNT,
                BUYING_RATE,
                SELLING_RATE,
                TODAY);
    }
}
