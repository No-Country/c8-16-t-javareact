package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.models.entity.AccountEntity;

public class Income extends Transaction{
    @Override
    public void updateBalance(AccountEntity account, Double amountIncome) {
        account.setBalance(account.getBalance()+amountIncome);
    }
}
