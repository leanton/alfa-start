package me.antonle.alfastart.rates.controller;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import me.antonle.alfastart.common.api.RateAPI;
import me.antonle.alfastart.rates.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
@AutoJsonRpcServiceImpl
public class RateController implements RateAPI {

    @Autowired
    private RateService rateService;

    @Override
    public BigDecimal getRate(String fromCcy, String toCcy) {
        return BigDecimal.ONE;
    }

}
