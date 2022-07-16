package com.maveric.account.controller;


import com.maveric.account.model.Account;
import com.maveric.account.model.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers/")
public class AccountController {

    @PostMapping("{customerId}/accounts")
    public ResponseEntity<Account> createAccount(@PathVariable String customerId, @RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(AccountService.createAccount(accountDTO));
    }

}
