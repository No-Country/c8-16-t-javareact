package com.nocountry.wallet.models.response;

import com.nocountry.wallet.utils.enumeration.TypeTransaction;
import lombok.Data;

import java.util.Date;


@Data
public class TransSimpleDTO {
    private Double amount;
    private TypeTransaction type;
    private Long accountId;
    private Date transactionDate;
}
