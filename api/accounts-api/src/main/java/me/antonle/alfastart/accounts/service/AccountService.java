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

    @Transactional
    public void deposit(Long accountID, BigDecimal depositAmout) {
        Account account = accountRepository.findOne(accountID);
        account.setBalance(account.getBalance().add(depositAmout));
    }
}
