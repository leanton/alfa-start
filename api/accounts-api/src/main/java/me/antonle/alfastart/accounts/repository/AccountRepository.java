package me.antonle.alfastart.accounts.repository;

import me.antonle.alfastart.common.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
