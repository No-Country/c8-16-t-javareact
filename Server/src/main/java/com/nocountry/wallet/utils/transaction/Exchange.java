package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.models.entity.AccountEntity;

public class Exchange extends Transaction{
    AccountEntity userAccountOrigin;
    AccountEntity userAccountDestiny;
    Double amountExchange;

    Double amountConverted;
    Double cryptoValue;
    String currencyType;
    private void convertAmount(){
        Double amount = this.amountExchange;
        this.amountConverted = amount/cryptoValue;
    }

    @Override
    public void updateBalance(AccountEntity account, Double amount) {
        this.userAccountOrigin.setBalance(userAccountOrigin.getBalance()-amountExchange);
        this.userAccountDestiny.setBalance(userAccountDestiny.getBalance()+amountConverted);
    }
}
