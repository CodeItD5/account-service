package com.maveric.account.service;


import com.maveric.account.exception.AccountNotFoundException;
import com.maveric.account.exception.UserNotFoundException;
import com.maveric.account.model.Account;
import com.maveric.account.model.ApplicationError;
import com.maveric.account.repository.AccountRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


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

    public List<Account> getUserAccounts(String customerId, Integer page, Integer pageSize){
        Pageable paging = PageRequest.of(page, pageSize);
        List<Account> accountList = new ArrayList<>();
        Page<Account> accounts = repository.findByCustomerId(customerId, paging);
        if(!accounts.getContent().isEmpty()) {
            for (Account user : accounts) {
                accountList.add(user);
            }
        }else{
            throw new UserNotFoundException("User with customerID "+ customerId+" does not exists");
        }
        return accountList;
    }

    public Account getUserAccountByAccountId(String customerId, String accountId) {
        return repository.findByCustomerIdAndId(customerId, accountId).orElseThrow(()-> new AccountNotFoundException(accountNotFound));

    }

    @Override
    public ApplicationError deleteUserAccountByAccountId(String customerId, String accountId) {
        Account accountFound = repository.findByCustomerIdAndId(customerId, accountId).orElseThrow(() -> new AccountNotFoundException(accountNotFound));
        repository.delete(accountFound);
        return new ApplicationError(HttpStatus.OK, "Account has been deleted for given customer");

    }
    public Account updateUserAccountByAccountId(String customerId, String accountId, Account account) {
        Account accountFound = repository.findByCustomerIdAndId(customerId,accountId).orElseThrow(()-> new AccountNotFoundException(accountNotFound));
        Date updatedDate = new Date(Calendar.getInstance().getTime().getTime());
        accountFound.setUpdatedAt(updatedDate);
        accountFound.setCustomerId(account.getCustomerId());
        accountFound.setType(account.getType());
        return repository.save(accountFound);
    }
}


