package com.transaction.repository;

import com.transaction.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);
}
