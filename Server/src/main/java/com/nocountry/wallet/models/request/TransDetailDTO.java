package com.nocountry.wallet.models.request;

import com.nocountry.wallet.utils.enumeration.TypeTransaction;
import lombok.Data;

import java.time.Instant;

@Data
public class TransDetailDTO {

    Double amount;
    String description;
    Long account_id;
    TypeTransaction type;
    Instant transactionDate;
}
