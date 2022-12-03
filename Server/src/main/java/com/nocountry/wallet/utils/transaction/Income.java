package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.models.entity.AccountEntity;

public class Income extends Transaction{
    AccountEntity userAccount;
    Double amountIncome;

    @Override
    public void updateBalance(AccountEntity account, Double amount) {
        this.userAccount.setBalance(userAccount.getBalance()+amountIncome);
    }
}
