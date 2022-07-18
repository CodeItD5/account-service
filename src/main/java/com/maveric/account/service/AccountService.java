package com.maveric.account.service;

import com.maveric.account.model.Account;
import com.maveric.account.model.ApplicationError;


import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    List<Account> getUserAccounts(String customerId, Integer page, Integer pageSize);

    Account getUserAccountByAccountId(String customerId, String accountId);


    ApplicationError deleteUserAccountByAccountId(String customerId, String accountId);

    Account updateUserAccountByAccountId(String customerId, String accountId, Account account);



}
