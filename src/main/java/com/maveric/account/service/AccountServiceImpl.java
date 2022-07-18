package com.maveric.account.service;


import com.maveric.account.model.Account;
import com.maveric.account.model.ApplicationError;
import com.maveric.account.repository.AccountRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;


@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepo repository;

    String accountNotFound = "Account not found";

    public AccountServiceImpl(AccountRepo repository) {
        this.repository = repository;
    }

    @Override
    public Account createAccount(Account account) {
        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        account.setCreatedAt(currentDate);
        account.setUpdatedAt(currentDate);
        return repository.insert(account);
    }

    public Page<Account> getUserAccounts(String customerId, Integer page, Integer pageSize){
        Pageable paging = PageRequest.of(page, pageSize);
        return repository.findByCustomerId(customerId, paging);
    }

    public Account getUserAccountByAccountId(String customerId, String accountId) {
        return repository.findByCustomerIdAndId(customerId, accountId).orElseThrow(()-> new NullPointerException(accountNotFound));

    }

    public Account updateUserAccountByAccountId(String customerId, String accountId, Account account) {
        Account accountFound = repository.findByCustomerIdAndId(customerId,accountId).orElseThrow(()-> new NullPointerException(accountNotFound));
        Date updatedDate = new Date(Calendar.getInstance().getTime().getTime());
        accountFound.setUpdatedAt(updatedDate);
        accountFound.setCustomerId(account.getCustomerId());
        accountFound.setType(account.getType());
        return repository.save(accountFound);
    }
}


