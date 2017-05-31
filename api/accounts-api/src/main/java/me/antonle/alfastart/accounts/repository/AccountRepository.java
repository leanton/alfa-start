package me.antonle.alfastart.accounts.repository;

import me.antonle.alfastart.common.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
