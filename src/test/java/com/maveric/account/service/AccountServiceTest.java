package com.maveric.account.service;

import com.maveric.account.exception.AccountNotFoundException;
import com.maveric.account.exception.UserNotFoundException;
import com.maveric.account.model.Account;
import com.maveric.account.model.ApplicationError;
import com.maveric.account.repository.AccountRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class AccountServiceTest {

    private AccountRepo accountRepo = Mockito.mock(AccountRepo.class);

    Date getDate = new Date();
    Account account = Account.builder().customerId("1").type("CURRENT").createdAt(getDate).updatedAt(new Date()).build();
    Account accountFound = Account.builder().customerId("1").type("CURRENT").createdAt(getDate).updatedAt(getDate).build();

    Account accountNotFound = Account.builder().build();
    ApplicationError applicationError =  new ApplicationError(HttpStatus.OK, "Account has been deleted for given customer");

    AccountServiceImpl accountService = new AccountServiceImpl(accountRepo);

    @Test
    @DisplayName("Test to check service method for creatingAccount")
    void createAccountService(){
        when(accountRepo.insert(account)).thenReturn(account);
        Assertions.assertThat(accountService.createAccount(account).getCustomerId()).isEqualTo(account.getCustomerId());
    }

    @Test
    @DisplayName("Test to check service method for getUsersAccount")
    void getUsersAccountService(){
        Pageable pageable = PageRequest.of(0,2);
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        final Page<Account> page = new PageImpl<>(accounts);
        when(accountRepo.findByCustomerId("1",pageable)).thenReturn(page);
        Assertions.assertThat(accountService.getUserAccounts("1",0,2)).isEqualTo(accounts);
    }

    @Test
    @DisplayName("Test to check if service method for getUsersAccount throws Exception")
    void getUsersAccountServiceExceptionTest(){
        Pageable pageable = PageRequest.of(0,2);
        List<Account> accounts = new ArrayList<>();
        final Page<Account> page = new PageImpl<>(accounts);
        when(accountRepo.findByCustomerId("1",pageable)).thenReturn(page);
        Assertions.assertThatThrownBy(()->accountService.getUserAccounts("1",0,2)).isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("Test to check service method for getUserAccountByAccountId")
    void getUserAccountByAccountIdService(){
        when(accountRepo.findByCustomerIdAndId("1","1")).thenReturn(Optional.of(accountFound));
        Assertions.assertThat(accountService.getUserAccountByAccountId("1","1")).isEqualTo(accountFound);
    }

    @Test
    @DisplayName("Test to check service method for getUserAccountByAccountId throws Exception")
    void getUserAccountByAccountIdServiceExceptionTest(){
        when(accountRepo.findByCustomerIdAndId("1","1")).thenThrow((new AccountNotFoundException("Account not found")));
        Assertions.assertThatThrownBy(()->accountService.getUserAccountByAccountId("1","1")).isInstanceOf(AccountNotFoundException.class);
    }

    @Test
    @DisplayName("Test to check service method for deleteUserAccountByAccountId")
    void deleteUserAccountByAccountIdService(){
        when(accountRepo.findByCustomerIdAndId("1","1")).thenReturn(Optional.of(account));
        Assertions.assertThat(accountService.deleteUserAccountByAccountId("1","1")).isEqualTo(applicationError);
    }

    @Test
    @DisplayName("Test to check service method for updateUserAccountByAccountId")
    void updateUserAccountByAccountIdService(){
        when(accountRepo.findByCustomerIdAndId("1","1")).thenReturn(Optional.of(accountFound));
        when(accountRepo.save(accountFound)).thenReturn(account);
        Assertions.assertThat(accountService.updateUserAccountByAccountId("1","1",account)).isEqualTo(account);
    }
}
