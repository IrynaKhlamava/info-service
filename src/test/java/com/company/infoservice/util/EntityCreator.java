package com.company.infoservice.util;

import com.company.infoservice.entity.ExchangeRate;

import static com.company.infoservice.util.TestConstants.*;

public class EntityCreator {
    public static ExchangeRate getExchangeRate() {
        return new ExchangeRate(UUID, COUNTRY, CURRENCY_NAME, CURRENCY_CODE, AMOUNT, BUYING_RATE, SELLING_RATE, TODAY);
    }
}
