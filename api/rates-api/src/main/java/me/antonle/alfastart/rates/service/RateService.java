package me.antonle.alfastart.rates.service;

import me.antonle.alfastart.common.Routes;
import me.antonle.alfastart.common.domain.Ccy;
import me.antonle.alfastart.rates.entity.RateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class RateService {

    @Autowired
    private RestTemplate restTemplate;

    public BigDecimal getRate(Ccy fromCcy, Ccy toCcy) {
        ResponseEntity<RateResponse> response = restTemplate.getForEntity(
                String.format(Routes.RATE_PROVIDER, fromCcy.getName(), toCcy.getName()),
                RateResponse.class);
        return response.getBody().getRates().get(toCcy.getName());
    }
}
