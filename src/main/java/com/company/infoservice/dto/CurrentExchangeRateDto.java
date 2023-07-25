package com.company.infoservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrentExchangeRateDto implements Serializable {

        String date;
        Rates rates;

    }