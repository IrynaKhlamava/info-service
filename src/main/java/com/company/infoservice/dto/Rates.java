package com.company.infoservice.dto;

import com.company.infoservice.exception.ExchangeRateServiceException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

import static com.company.infoservice.util.Constants.EXCHANGE_RATE_NOT_FOUND;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    @JsonProperty("USD")
    public BigDecimal usd;
    @JsonProperty("CHF")
    public BigDecimal chf;
    @JsonProperty("EUR")
    public BigDecimal eur;
    @JsonProperty("JPY")
    public BigDecimal jpy;
    @JsonProperty("GBP")
    public BigDecimal gbp;
    @JsonProperty("AUD")
    public BigDecimal aud;
    @JsonProperty("CNY")
    public BigDecimal cny;
    @JsonProperty("DKK")
    public BigDecimal dkk;

    public BigDecimal getForCurrency(String currencyName) {
        return switch (currencyName.toUpperCase()) {
            case "USD" -> usd;
            case "CHF" -> chf;
            case "EUR" -> eur;
            case "JPY" -> jpy;
            case "GBP" -> gbp;
            case "AUD" -> aud;
            case "CNY" -> cny;
            case "DKK" -> dkk;
            default -> throw new ExchangeRateServiceException(EXCHANGE_RATE_NOT_FOUND);
        };
    }
}

