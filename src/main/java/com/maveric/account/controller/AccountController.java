package com.maveric.account.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers/")
public class AccountController {

    @PostMapping("{customerId}/accounts")
    public void createAccount(@PathVariable String customerId){

    }

}
