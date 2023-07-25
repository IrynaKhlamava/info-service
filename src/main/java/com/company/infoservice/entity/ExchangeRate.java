package com.company.infoservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "exchange_rates")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Cacheable
@Builder
public class ExchangeRate implements Serializable {

    @Id
    @GeneratedValue
    @UuidGenerator
    UUID id;
    String country;
    String currencyName;
    String currencyCode;
    BigDecimal amount;
    BigDecimal buyingRate;
    BigDecimal sellingRate;
    LocalDate updateDate;
}
