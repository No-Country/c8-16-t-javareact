package com.nocountry.wallet.models.response;


import lombok.Data;


@Data
public class AccountDTOSlim {
    private String currency;
    private Double balance;
}
