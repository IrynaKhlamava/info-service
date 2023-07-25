package com.company.infoservice.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class TestConstants {
    public static final UUID UUID = randomUUID();
    public static final String COUNTRY = "USA";
    public static final String CURRENCY_NAME = "Dollar";
    public static final String CURRENCY_CODE = "USD";
    public static final BigDecimal AMOUNT = BigDecimal.ONE;
    public static final BigDecimal BUYING_RATE = BigDecimal.TEN.setScale(Constants.DIGITS_AFTER_COMA,
            Constants.ROUNDING_MODE);
    public static final BigDecimal SELLING_RATE = BigDecimal.valueOf(11).setScale(Constants.DIGITS_AFTER_COMA,
            Constants.ROUNDING_MODE);
    public static final LocalDate TODAY = LocalDate.now();
}
