package me.antonle.alfastart.rates.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RateResponse {

    private String base;
    private Map<String, BigDecimal> rates;

}
