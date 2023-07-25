package com.company.infoservice.controller;

import com.company.infoservice.dto.ExchangeRateDto;
import com.company.infoservice.service.ExchangeRateService;
import com.company.infoservice.util.DtoCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ExchangeRateController.class)
class ExchangeRateControllerTest {

    @MockBean
    private ExchangeRateService exchangeRateService;
    @InjectMocks
    private ExchangeRateController exchangeRateController;
    @Autowired
    private MockMvc mockMvc;
    private List<ExchangeRateDto> exchangeRatesDto;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void init() {
        exchangeRatesDto = new ArrayList<>();
        exchangeRatesDto.add(ExchangeRateDto.builder().currencyCode("USD").buyingRate(BigDecimal.valueOf(0.84)).build());
    }


    @Test
    void testControllerGetExchangeRates() throws Exception {
        when(exchangeRateService.getExchangeRatesDto()).thenReturn(exchangeRatesDto);

        mockMvc.perform(get("/exchange-rates")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(exchangeRatesDto)));

        verify(exchangeRateService, times(1)).getExchangeRatesDto();
    }

    @Test
    void testControllerGetExchangeRate_ForUsd() throws Exception {

        when(exchangeRateService.exchange("usd", BigDecimal.ONE)).thenReturn(DtoCreator.getExchangeRateDto());
        mockMvc.perform(get("/exchange-rates/{id}", "usd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(DtoCreator.getExchangeRateDto())));

        verify(exchangeRateService, times(1)).exchange("usd", BigDecimal.ONE);
    }
}