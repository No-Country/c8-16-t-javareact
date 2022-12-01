package com.nocountry.wallet.models.response;


import com.nocountry.wallet.utils.enumeration.TypeTransaction;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDto {

  private Long id;
  private Double amount;
  private TypeTransaction type;
  private String description;
  private Long accountId;
  private Date transactionDate;
}