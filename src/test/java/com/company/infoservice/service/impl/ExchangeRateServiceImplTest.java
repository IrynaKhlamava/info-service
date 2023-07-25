package com.company.infoservice.service.impl;

import com.company.infoservice.entity.ExchangeRate;
import com.company.infoservice.exception.NotFoundException;
import com.company.infoservice.mapper.ExchangeRateMapper;
import com.company.infoservice.repository.ExchangeRateRepository;
import com.company.infoservice.util.DtoCreator;
import com.company.infoservice.util.EntityCreator;
import com.company.infoservice.util.ExchangeRateFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceImplTest {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;
    @Mock
    private ExchangeRateMapper exchangeRateMapper;
    @Mock
    ExchangeRateFeignClient exchangeRateFeignClient;
    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;
    @Mock
    private List<ExchangeRate> exchangeRates;

    @BeforeEach
    public void init() {
        exchangeRates = new ArrayList<>();
        exchangeRates.add(ExchangeRate.builder().currencyCode("USD").buyingRate(BigDecimal.valueOf(0.84)).build());
    }

    @Test
    void updateExchangeRates() {
        ArgumentCaptor<String> apikeyCaptor = forClass(String.class);
        doReturn(ResponseEntity.of(Optional.of(DtoCreator.getCurrentExchangeRateDto())))
                .when(exchangeRateFeignClient)
                .getExchangeRatesByApi(apikeyCaptor.capture());
        exchangeRateService.updateExchangeRates();
        verify(exchangeRateRepository, times(1)).findAll();
    }

    @Test
    void exchange_Base() {
        when(exchangeRateRepository.findByCurrencyCode("USD")).thenReturn(Optional.of(EntityCreator.getExchangeRate()));
        when(exchangeRateMapper.toDto(EntityCreator.getExchangeRate())).thenReturn(DtoCreator.getExchangeRateDto());

        assertEquals(DtoCreator.getExchangeRateDto(), exchangeRateService.exchange("usd", BigDecimal.ONE));
    }

    @Test
    void getExchangeRate_Ok() {
        when(exchangeRateRepository.findByCurrencyCode("USD")).thenReturn(Optional.of(EntityCreator.getExchangeRate()));
        assertEquals(EntityCreator.getExchangeRate(), exchangeRateService.getExchangeRate("usd"));
    }

    @Test
    void getExchangeRate_NotFound() {
        when(exchangeRateRepository.findByCurrencyCode(any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> exchangeRateService.getExchangeRate("usd"));
    }
}

