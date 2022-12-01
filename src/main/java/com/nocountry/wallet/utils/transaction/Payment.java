package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.models.entity.AccountEntity;

public class Payment extends Transaction{

    @Override
    public void updateBalance(AccountEntity account, Double amountPayment) {
        account.setBalance(account.getBalance()-amountPayment);
    }
}
