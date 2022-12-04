package com.nocountry.wallet.models.response;

import lombok.Data;

import java.time.Instant;

@Data
public class AccountWithoutUserDTO {

    private Integer id;
    private String currency;
    private Double transactionLimit;
    private Double balance;
    private Instant updateDate;
    private Instant creationDate;
}