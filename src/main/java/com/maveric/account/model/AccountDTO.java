package com.maveric.account.model;
import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class AccountDTO {

    @NotNull(message="Account type can't be empty")
    private AccountType type;


    @NotNull(message = "Customer Id can't be empty")
    private String customerId;
}
