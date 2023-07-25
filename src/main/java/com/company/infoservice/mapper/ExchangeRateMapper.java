package com.company.infoservice.mapper;

import com.company.infoservice.dto.ExchangeRateDto;
import com.company.infoservice.entity.ExchangeRate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {

    ExchangeRateDto toDto(ExchangeRate exchangeRate);

    List<ExchangeRateDto> toDtoList(List<ExchangeRate> list);
}
