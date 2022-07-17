package com.maveric.account.service;


import com.maveric.account.model.Account;
import com.maveric.account.repository.AccountRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepo repository;

    public AccountServiceImpl(AccountRepo repository) {
        this.repository = repository;
    }

    @Override
    public Account createAccount(Account account) {
        return repository.insert(account);
    }
}


