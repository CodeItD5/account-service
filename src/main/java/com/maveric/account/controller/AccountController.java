package com.maveric.account.controller;

import com.maveric.account.model.Account;
import com.maveric.account.model.AccountDTO;
import com.maveric.account.model.ApplicationError;
import com.maveric.account.service.NextSequenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.maveric.account.service.AccountServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class AccountController {

    @Autowired
    ModelMapper modelMapper;

    private final AccountServiceImpl accountService;

    private final NextSequenceService nextSequenceService;


    public AccountController(AccountServiceImpl accountService, NextSequenceService nextSequenceService) {
        this.accountService = accountService;
        this.nextSequenceService = nextSequenceService;
    }

    @PostMapping("/{customerId}/accounts")
    private ResponseEntity<Account> createAccount(@Valid @RequestBody AccountDTO accountDTO, @PathVariable String customerId){
        accountDTO.setCustomerId(customerId);
        Account account = convertToEntity(accountDTO);
        account.setId(String.valueOf(nextSequenceService.getNextSequence("customSequences")));
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}/accounts")
    private ResponseEntity<List<Account>> getUserAccounts(@PathVariable String customerId, @RequestParam Integer page, @RequestParam Integer pageSize){
        return new ResponseEntity<>(accountService.getUserAccounts(customerId, page, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{customerId}/accounts/{accountId}")
    private ResponseEntity<Account> getUserAccountByAccountId(@PathVariable String customerId, @PathVariable String accountId){
        return new ResponseEntity<>(accountService.getUserAccountByAccountId(customerId, accountId), HttpStatus.OK);
    }


    @DeleteMapping("/{customerId}/accounts/{accountId}")
    private ResponseEntity<ApplicationError> deleteUserAccountByAccountId(@PathVariable String customerId, @PathVariable String accountId){
        return new ResponseEntity<>(accountService.deleteUserAccountByAccountId(customerId, accountId), HttpStatus.OK);
    }


    @PutMapping("/{customerId}/accounts/{accountId}")
    private ResponseEntity<Account> updateUserAccountByAccountId(@PathVariable String customerId, @PathVariable String accountId, @RequestBody AccountDTO accountDTO){
        Account account = convertToEntity(accountDTO);
        return new ResponseEntity<>(accountService.updateUserAccountByAccountId(customerId, accountId, account), HttpStatus.OK);
    }


    private Account convertToEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }


}
