package com.transaction.process;

import com.transaction.entity.Account;
import com.transaction.pojo.AccountResponse;
import com.transaction.repository.AccountRepo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAccountsByUserName {

    private final AccountRepo accountRepo;

    public GetAccountsByUserName(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Cacheable(value = "accountsByUser", key = "#username")
    public List<AccountResponse> getAccounts(String username) {
        List<AccountResponse> result = new ArrayList<>();
        try {
            List<Account> al = accountRepo.findByAllWithClient(username);
            if (!al.isEmpty()){
                buildAccountResponseList(username, al, result);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private void buildAccountResponseList(String username, List<Account> al, List<AccountResponse> result) {
        AccountResponse accountResponse;
        for (Account a : al){
            accountResponse = new AccountResponse();
            accountResponse.setAccountNumber(a.getAccountNumber());
            accountResponse.setBalance(a.getBalance());
            accountResponse.setUsername(username);
            accountResponse.setCreatedDate(a.getCreatedDate());
            result.add(accountResponse);
        }
    }

}
