package me.antonle.alfastart.common.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import me.antonle.alfastart.common.entity.Account;

import javax.annotation.Nullable;
import java.math.BigDecimal;

@JsonRpcService("/api/account")
public interface AccountAPI {

    Account create(@JsonRpcParam("name") String accountName,
                   @JsonRpcParam("ccy") String ccy);

    @Nullable
    Account get(@JsonRpcParam("accountID") Long accountId);

    Account deposit(@JsonRpcParam("accountID") Long accountId,
                    @JsonRpcParam("depositAmount")BigDecimal depositAmt);

    String test(@JsonRpcParam("testString") String testString);

}
