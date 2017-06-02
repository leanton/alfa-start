package me.antonle.alfastart.common.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import me.antonle.alfastart.common.Routes;

import java.math.BigDecimal;

@JsonRpcService(Routes.RATES_API)
public interface RateAPI {

    BigDecimal getRate(@JsonRpcParam("fromCcy") String fromCcy,
                       @JsonRpcParam("toCcy") String toCcy);

}
