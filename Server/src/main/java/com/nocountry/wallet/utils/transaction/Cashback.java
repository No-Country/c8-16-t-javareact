package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.utils.enumeration.CashbackAmount;

public class Cashback extends Transaction{


    @Override
    public void updateBalance(AccountEntity account, Double amount) {
        account.setBalance(account.getBalance()+ amount*CashbackAmount.CASHBACK_AMOUNT.getPercent());
    }
}
