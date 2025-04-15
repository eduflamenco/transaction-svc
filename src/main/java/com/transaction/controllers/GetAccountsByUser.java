package com.transaction.controllers;

import com.transaction.pojo.AccountResponse;
import com.transaction.process.GetAccountsByUserName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/GetAccounts")
public class GetAccountsByUser {

    private final GetAccountsByUserName getAccountsByUserName;

    public GetAccountsByUser(GetAccountsByUserName getAccountsByUserName) {
        this.getAccountsByUserName = getAccountsByUserName;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> GetAccountsByUserName(@PathVariable String username) {
        List<AccountResponse> response = getAccountsByUserName.getAccounts(username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
