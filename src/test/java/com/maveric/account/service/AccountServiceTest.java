package com.maveric.account.service;

import com.maveric.account.model.Account;
import com.maveric.account.repository.AccountRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class AccountServiceTest {

    private AccountRepo accountRepo = Mockito.mock(AccountRepo.class);

    Account account = Account.builder().id("1").customerId("1").type("CURRENT").build();
    AccountServiceImpl accountService = new AccountServiceImpl(accountRepo);

    @Test
    @DisplayName("Test to check service method for creatingAccount")
    void createAccountService(){
        when(accountRepo.insert(account)).thenReturn(account);
        Assertions.assertThat(accountService.createAccount(account).getCustomerId()).isEqualTo(account.getCustomerId());
    }


}
