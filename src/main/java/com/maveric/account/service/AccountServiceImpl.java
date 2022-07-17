package com.maveric.account.service;


import com.maveric.account.model.Account;
import com.maveric.account.repository.AccountRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Account> getUserAccounts(String customerId, Integer page, Integer pageSize){
        Pageable paging = PageRequest.of(page, pageSize);
        return repository.findByCustomerId(customerId, paging);
    }
}


