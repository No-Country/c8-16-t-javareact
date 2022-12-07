package com.nocountry.wallet.models.request;

import com.nocountry.wallet.models.response.UserDetailDTO;
import lombok.Data;

import java.time.Instant;

@Data
public class AccountDTO {

    private Integer id;
    private String currency;
    private Double transactionLimit;
    private Double balance;
    private Instant updateDate;
    private Instant creationDate;
    private UserDetailDTO user;
    private boolean softDelete = Boolean.FALSE;
}
