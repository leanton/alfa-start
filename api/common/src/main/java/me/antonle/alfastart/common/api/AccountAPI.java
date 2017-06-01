package me.antonle.alfastart.common.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import me.antonle.alfastart.common.Routes;
import me.antonle.alfastart.common.domain.Ccy;
import me.antonle.alfastart.common.entity.Account;

import javax.annotation.Nullable;
import java.math.BigDecimal;

@JsonRpcService(Routes.ACCOUNTS_API)
public interface AccountAPI {

    Account create(@JsonRpcParam("name") String accountName,
                   @JsonRpcParam("ccy") Ccy ccy);

    @Nullable
    Account get(@JsonRpcParam("accountID") Long accountId);

    Account deposit(@JsonRpcParam("accountID") Long accountId,
                    @JsonRpcParam("depositAmount")BigDecimal depositAmt);

}
