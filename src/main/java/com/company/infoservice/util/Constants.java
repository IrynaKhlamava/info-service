package com.company.infoservice.util;

import lombok.experimental.UtilityClass;

import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_UP;

@UtilityClass
public class Constants {
    public static final String EXCHANGE_RATE_NOT_FOUND = "Such an exchange rate was not found";
    public static final int DIGITS_AFTER_COMA = 6;
    public static final RoundingMode ROUNDING_MODE = HALF_UP;
}
