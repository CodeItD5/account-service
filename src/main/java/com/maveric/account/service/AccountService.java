package com.maveric.account.service;


import com.maveric.account.model.Account;
import com.maveric.account.model.AccountDTO;
import com.maveric.account.repository.AccountRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    ModelMapper modelMapper;
    private final AccountRepo accountRepo;

    public AccountService(AccountService accountService, AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account createAccount(AccountDTO accountDTO){
        Account account = convertToEntity(accountDTO);
        Account accountCreated =  accountRepo.insert(account);
        return accountCreated;
    }

    private Account convertToEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }

}


