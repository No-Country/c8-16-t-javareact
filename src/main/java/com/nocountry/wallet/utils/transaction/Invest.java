package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.utils.enumeration.InvestValues;

public class Invest extends Transaction{
    AccountEntity userAccount;
    Double amountIncome;
    Double amountWithInvest;
    String currencyType;

    private void amountPlus(){
        this.amountWithInvest = amountIncome + amountIncome/InvestValues.valueOf(currencyType).getInterest();
    }

    @Override
    public void updateBalance(AccountEntity account, Double amountIncome) {
        this.userAccount.setBalance(userAccount.getBalance()+amountIncome);
    }
}


