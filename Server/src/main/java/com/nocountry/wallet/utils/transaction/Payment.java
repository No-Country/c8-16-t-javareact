package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.exception.TransactionException;
import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Payment extends Transaction{

    @Override
    public void updateBalance(AccountEntity account, Double amountPayment) {
        if(account.getBalance()<amountPayment){
            throw new TransactionException(ErrorEnum.INSUFFICIENT_BALANCE.getMessage());
        }
        log.info("Update balance account with amount payment");
        account.setBalance(account.getBalance()-amountPayment);
    }
}
