package com.transaction.repository;

import com.transaction.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);

    @Query("SELECT a From Account a JOIN fetch a.client c where c.username = :username and a.isActive = true")
    List<Account> findByAllWithClient(@Param("username") String username);
}
