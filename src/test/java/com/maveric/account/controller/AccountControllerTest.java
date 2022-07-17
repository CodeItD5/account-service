package com.maveric.account.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.account.model.Account;
import com.maveric.account.model.AccountDTO;
import com.maveric.account.model.AccountType;
import com.maveric.account.service.AccountServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AccountControllerTest {

    @MockBean
    private AccountServiceImpl accountService;


    @Autowired
    private MockMvc mockMvc;

    Account account = Account.builder().id("1").customerId("1").type("CURRENT").build();
    AccountDTO accountDTO = AccountDTO.builder().type(AccountType.CURRENT).customerId("1").build();

    @Test
    @DisplayName("Test to check if createAccount endpoint works fine")
    void createAccountTest() throws Exception {

        given(accountService.createAccount(account)).willReturn(account);
        mockMvc.perform(post("/api/v1/customers/1/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(accountDTO))).andExpect(status().isCreated());
    }


    private String asJsonString(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }
}
