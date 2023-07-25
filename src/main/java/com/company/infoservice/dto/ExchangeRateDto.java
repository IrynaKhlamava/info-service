package com.company.infoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -306849483607867099L;
    UUID id;
    String country;
    String currencyName;
    String currencyCode;
    BigDecimal amount;
    BigDecimal buyingRate;
    BigDecimal sellingRate;
    LocalDate updateDate;
}
