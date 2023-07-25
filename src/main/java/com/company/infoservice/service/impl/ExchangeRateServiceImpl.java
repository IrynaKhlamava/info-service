package com.company.infoservice.service.impl;

import com.company.infoservice.dto.CurrentExchangeRateDto;
import com.company.infoservice.dto.ExchangeRateDto;
import com.company.infoservice.dto.Rates;
import com.company.infoservice.entity.ExchangeRate;
import com.company.infoservice.exception.ExchangeRateServiceException;
import com.company.infoservice.exception.NotFoundException;
import com.company.infoservice.mapper.ExchangeRateMapper;
import com.company.infoservice.repository.ExchangeRateRepository;
import com.company.infoservice.service.ExchangeRateService;
import com.company.infoservice.util.Constants;
import com.company.infoservice.util.ExchangeRateFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.company.infoservice.util.Constants.DIGITS_AFTER_COMA;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Value("${exchange-rate.apikey}")
    private String apiKey;
    @Value("${exchange-rate.sale-percent}")
    private BigDecimal salePercent;

    private final ExchangeRateFeignClient exchangeRateFeignClient;
    private final ExchangeRateMapper exchangeRateMapper;
    private final ExchangeRateRepository exchangeRateRepository;
    private final RedisTemplate<UUID, ExchangeRateDto> redisTemplate;

    private static BigDecimal getRate(String currencyCode, Rates rates, BigDecimal amount) {
        return amount.divide(rates.getForCurrency(currencyCode), DIGITS_AFTER_COMA, Constants.ROUNDING_MODE);
    }

    @Cacheable("exchange-rates")
    @Override
    @Transactional(readOnly = true)
    public List<ExchangeRateDto> getExchangeRatesDto() {
        List<ExchangeRateDto> dtoList = exchangeRateMapper.toDtoList(exchangeRateRepository.findAll());
        dtoList.forEach(rateDto -> redisTemplate.opsForValue().set(rateDto.getId(), rateDto));
        return dtoList;
    }

    private BigDecimal getSellingRate(BigDecimal buyingRate) {
        return buyingRate.multiply(salePercent).setScale(DIGITS_AFTER_COMA, Constants.ROUNDING_MODE);
    }

    @Override
    @Transactional
    @CacheEvict("exchange-rates")
    public void updateExchangeRates() {
        CurrentExchangeRateDto currentRatesDto = getCurrentExchangeRatesByApi();
        exchangeRateRepository.findAll().forEach(rate -> {
            rate.setBuyingRate(getRate(rate.getCurrencyCode(), currentRatesDto.getRates(), rate.getAmount()));
            rate.setSellingRate(getSellingRate(rate.getBuyingRate()));
            rate.setUpdateDate(LocalDate.parse(currentRatesDto.getDate()));
            exchangeRateRepository.save(rate);
        });
    }

    @Override
    public ExchangeRateDto exchange(String currency, BigDecimal amount) {
        ExchangeRateDto dto = exchangeRateMapper.toDto(getExchangeRate(currency));
        calculate(dto.getBuyingRate(), amount);
        dto.setBuyingRate(calculate(dto.getBuyingRate(), amount));
        dto.setSellingRate(calculate(dto.getSellingRate(), amount));
        dto.setAmount(amount);
        return dto;
    }

    private BigDecimal calculate(BigDecimal rate, BigDecimal amount) {
        return rate.multiply(amount).setScale(DIGITS_AFTER_COMA, Constants.ROUNDING_MODE);
    }

    public ExchangeRate getExchangeRate(String currency) {
        return exchangeRateRepository.findByCurrencyCode(currency.toUpperCase())
                .orElseThrow(() -> new NotFoundException("Currency '%s' not found", currency));
    }

    private CurrentExchangeRateDto getCurrentExchangeRatesByApi() {
        try {
            ResponseEntity<CurrentExchangeRateDto> currencyResponseEntity = exchangeRateFeignClient.getExchangeRatesByApi(apiKey);
            CurrentExchangeRateDto currentExchangeRateDto = currencyResponseEntity.getBody();
            log.info("Got current exchange rates by api " + currentExchangeRateDto);
            return currentExchangeRateDto;
        } catch (Exception ex) {
            log.error("Getting current exchange rate by api failed: " + ex.getMessage());
            throw new ExchangeRateServiceException("Get current exchange rate by api failed " + ex.getMessage());
        }
    }

}
