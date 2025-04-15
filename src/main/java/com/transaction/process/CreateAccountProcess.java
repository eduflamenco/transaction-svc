package com.transaction.process;

import com.transaction.entity.Account;
import com.transaction.entity.Client;
import com.transaction.pojo.AccountResponse;
import com.transaction.repository.AccountRepo;
import com.transaction.repository.ClientRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateAccountProcess {

    private final ClientRepo clientRepo;
    private final AccountRepo accountRepo;

    public CreateAccountProcess(ClientRepo clientRepo, AccountRepo accountRepo) {
        this.clientRepo = clientRepo;
        this.accountRepo = accountRepo;
    }

    @Transactional
    @CacheEvict(value = "accountsByUser", key = "#username")
    public AccountResponse createAccount(String username, Double balance) {
        Account newAccount = null;
        try {
            Client existingClient = clientRepo.findByUsername(username);
            if (existingClient != null) {
                newAccount = accountRepo.save(new Account(username, balance, existingClient));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return buildCreateAccountResponse(newAccount);
    }

    private AccountResponse buildCreateAccountResponse(Account newAccount){
        AccountResponse response = new AccountResponse();
        if (newAccount != null) {
           response.setUsername(newAccount.getClient().getUsername());
           response.setAccountNumber(newAccount.getAccountNumber());
           response.setBalance(newAccount.getBalance());
           response.setCreatedDate(newAccount.getCreatedDate());
       }
        return response;
    }
}
