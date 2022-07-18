package com.maveric.account.service;

import com.maveric.account.model.Account;
import com.maveric.account.model.ApplicationError;
import org.springframework.data.domain.Page;

public interface AccountService {
    Account createAccount(Account account);

    Page<Account> getUserAccounts(String customerId, Integer page, Integer pageSize);

    Account getUserAccountByAccountId(String customerId, String accountId);

}
