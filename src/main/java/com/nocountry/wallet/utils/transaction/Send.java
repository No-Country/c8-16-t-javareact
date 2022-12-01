package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.models.entity.AccountEntity;

public class Send extends Transaction{
    AccountEntity userAccount;
    Double amountSend;

    @Override
    public void updateBalance(AccountEntity account, Double amountSend) {
        this.userAccount.setBalance(userAccount.getBalance()-amountSend);
    }
}
