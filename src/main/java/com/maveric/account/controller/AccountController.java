package com.maveric.account.controller;

import com.maveric.account.model.Account;
import com.maveric.account.model.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.maveric.account.service.AccountServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers/")
public class AccountController {

    @Autowired
    ModelMapper modelMapper;

    private final AccountServiceImpl accountService;


    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping("{customerId}/accounts")
    private ResponseEntity<Account> createAccount(@Valid @RequestBody AccountDTO accountDTO, @PathVariable String customerId){
        accountDTO.setCustomerId(customerId);
        Account account = convertToEntity(accountDTO);
        return new ResponseEntity<>(accountService.createAccount(account), HttpStatus.CREATED);
    }

    @GetMapping("{customerId}/accounts")
    private ResponseEntity<Page<Account>> getUserAccounts(@PathVariable String customerId, @RequestParam Integer page, @RequestParam Integer pageSize){
        return new ResponseEntity<>(accountService.getUserAccounts(customerId, page, pageSize), HttpStatus.OK);
    }
    

    private Account convertToEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }


}
