package com.transaction.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transaction.pojo.AccountResponse;
import com.transaction.pojo.CreateAccountRequest;
import com.transaction.process.CreateAccountProcess;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class CreateAccount {

    private final CreateAccountProcess createAccountProcess;

    public CreateAccount(CreateAccountProcess createAccountProcess) {
        this.createAccountProcess = createAccountProcess;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
         var resp = createAccountProcess.createAccount(createAccountRequest.username(), createAccountRequest.balance());
        return ResponseEntity.ok().body(resp);
    }
}
