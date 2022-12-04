package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.exception.TransactionException;
import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;

public class Send extends Transaction{

    @Override
    public void updateBalance(AccountEntity account, Double amountSend) {
        if(account.getBalance()<amountSend){
            throw new TransactionException(ErrorEnum.INSUFFICIENT_BALANCE.getMessage());
        }
        account.setBalance(account.getBalance()-amountSend);
    }
}
