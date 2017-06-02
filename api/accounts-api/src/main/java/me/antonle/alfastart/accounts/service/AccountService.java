package me.antonle.alfastart.accounts.service;

import me.antonle.alfastart.common.domain.Ccy;
import me.antonle.alfastart.common.entity.Account;
import me.antonle.alfastart.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account get(Long accountId) {
        return accountRepository.findOne(accountId);
    }

    @Transactional
    public Account create(String accountName, Ccy ccy) {
        Account account = new Account();
        account.setName(accountName);
        account.setCcy(ccy);
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    @Transactional
    public Account deposit(Long accountID, BigDecimal depositAmout) {
        Account account = get(accountID);
        account.setBalance(account.getBalance().add(depositAmout));
        return accountRepository.save(account);
    }

    @Transactional
    public Account withdraw(Long accountId, BigDecimal withdrawAmt) {
        Account account = get(accountId);
        account.setBalance(account.getBalance().subtract(withdrawAmt));
        return accountRepository.save(account);
    }

    @Transactional
    public Account transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = get(fromAccountId);
        Account toAccount = get(toAccountId);
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(Arrays.asList(fromAccount, toAccount));
        return fromAccount;
    }
}
