package com.neoteric.fullstackdemo.controller;


import com.neoteric.fullstackdemo.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo.model.Account;
import com.neoteric.fullstackdemo.service.AccountService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AccountController {

    @PostMapping(value = "/api/createAccount", consumes = "application/json", produces = "application/json")

    public Account getAccoutNumber(@RequestBody Account account)
            throws AccountCreationFailedException {
        AccountService accountService = new AccountService();
        String accountNumber = accountService.createAccount(account);
        account.setAccountNumber(accountNumber);
        //return accountService.createAccount(account);
        return account;
    }
}