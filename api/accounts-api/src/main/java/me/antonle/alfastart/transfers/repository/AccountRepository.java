package me.antonle.alfastart.transfers.repository;

import me.antonle.alfastart.transfers.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
