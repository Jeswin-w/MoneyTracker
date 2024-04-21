package com.saharaj.moneytracker.application.tracker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saharaj.moneytracker.application.tracker.model.Account;
import com.saharaj.moneytracker.application.tracker.repository.AccountRepository;

@RestController
@RequestMapping("account")
public class AccountController {

    AccountRepository accountRepository;

    AccountController (AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @PostMapping("add")
    public Account addAccount(@RequestBody Account accountDetails) {
        accountRepository.save(accountDetails);
        return accountDetails;
    }
    
}
