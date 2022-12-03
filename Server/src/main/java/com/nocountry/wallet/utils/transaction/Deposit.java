package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.models.entity.AccountEntity;

public class Deposit extends Transaction{

    @Override
    public void updateBalance(AccountEntity account, Double amountDeposit) {
        account.setBalance(account.getBalance()+amountDeposit);
    }
}
