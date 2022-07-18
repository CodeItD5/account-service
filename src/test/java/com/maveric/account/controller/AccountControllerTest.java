package com.maveric.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.account.model.Account;
import com.maveric.account.model.AccountDTO;
import com.maveric.account.model.AccountType;
import com.maveric.account.model.ApplicationError;
import com.maveric.account.service.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AccountControllerTest {

    @MockBean
    private AccountServiceImpl accountService;


    @Autowired
    private MockMvc mockMvc;

    Date getDate = new Date();
    Account account = Account.builder().id("1").customerId("1").type("CURRENT").createdAt(getDate).updatedAt(new Date()).build();
    Account accountFound = Account.builder().customerId("1").type("CURRENT").createdAt(getDate).updatedAt(getDate).build();
    AccountDTO accountDTO = AccountDTO.builder().type(AccountType.CURRENT).customerId("1").build();

    ApplicationError applicationError =  new ApplicationError(HttpStatus.OK, "Account has been deleted for given customer");

    @Test
    @DisplayName("Test to check if createAccount endpoint works fine")
    void createAccountTest() throws Exception {

        given(accountService.createAccount(account)).willReturn(account);
        mockMvc.perform(post("/api/v1/customers/1/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(accountDTO))).andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test to check if getUsersAccount endpoint works fine")
    void getUserAccountsTest() throws Exception {
        Account account = Account.builder().id("1").customerId("1").type("CURRENT").build();
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        final Page<Account> page = new PageImpl<>(accounts);
        given(accountService.getUserAccounts("1",0,2)).willReturn(page);
        mockMvc.perform(get("/api/v1/customers/1/accounts?page=0&pageSize=2"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test to check if getUserAccountByAccountId endpoint works fine")
    void getUserAccountByAccountIdTest() throws Exception {
        given(accountService.getUserAccountByAccountId("1","1")).willReturn(accountFound);
        mockMvc.perform(get("/api/v1/customers/1/accounts/1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test to check if deleteUserAccountByAccountId endpoint works fine")
    void deleteUserAccountByAccountIdTest() throws Exception {
        given(accountService.deleteUserAccountByAccountId("1", "1")).willReturn(applicationError);
        mockMvc.perform(delete("/api/v1/customers/1/accounts/1")).andExpect(status().isOk());

    }
    @Test
    @DisplayName("Test to check if updateUserAccountByAccountId endpoint works fine")
    void updateUserAccountByAccountIdTest() throws Exception {
        given(accountService.updateUserAccountByAccountId("1","1", accountFound)).willReturn(account);
        mockMvc.perform(get("/api/v1/customers/1/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(accountDTO)))
                .andExpect(status().isOk());
    }

    private String asJsonString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
