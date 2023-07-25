package com.company.infoservice.repository;

import com.company.infoservice.entity.ExchangeRate;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExchangeRateRepository extends ListCrudRepository<ExchangeRate, UUID> {
    Optional<ExchangeRate> findByCurrencyCode(String currencyCode);
}
