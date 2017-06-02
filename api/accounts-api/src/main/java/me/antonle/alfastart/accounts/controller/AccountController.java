package me.antonle.alfastart.accounts.controller;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import me.antonle.alfastart.accounts.repository.AccountRepository;
import me.antonle.alfastart.accounts.service.AccountService;
import me.antonle.alfastart.common.api.AccountAPI;
import me.antonle.alfastart.common.domain.Ccy;
import me.antonle.alfastart.common.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
@AutoJsonRpcServiceImpl
public class AccountController implements AccountAPI {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account create(String accountName, Ccy ccy) {
        Account account = new Account();
        account.setName(accountName);
        account.setCcy(ccy);
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    @Override
    public Account get(Long accountId) {
        return accountService.get(accountId);
    }

    @Override
    public Account deposit(Long accountId, BigDecimal depositAmt) {
        return accountService.deposit(accountId, depositAmt);
    }

    @Override
    public Account withdraw(Long accountId, BigDecimal withdrawAmt) {
        return accountService.withdraw(accountId, withdrawAmt);
    }

    @Override
    public Account transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        return accountService.transfer(fromAccountId, toAccountId, amount);
    }

}
