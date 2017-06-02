package me.antonle.alfastart.accounts.service;

import me.antonle.alfastart.common.entity.Account;
import me.antonle.alfastart.accounts.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account get(Long accountId) {
        return accountRepository.findOne(accountId);
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
}
