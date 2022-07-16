package com.maveric.account.service;


import com.maveric.account.model.Account;
import com.maveric.account.repository.AccountRepo;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.Calendar;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepo repository;

    public AccountServiceImpl(AccountRepo repository) {
        this.repository = repository;
    }

    @Override
    public Account createAccount(Account account) {
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        return repository.insert(account);
    }
}


