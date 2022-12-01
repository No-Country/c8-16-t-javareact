package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.utils.enumeration.TypeTransaction;

public class TransactionFactory {

    public static Transaction createTransaction(TypeTransaction typeTransaction){
        return switch (typeTransaction){
            case INCOME -> new Income();
            case INVEST -> new Invest();
            case PAYMENT -> new Payment();
            case DEPOSIT -> new Deposit();
            case SEND -> new Send();
            case EXCHANGE -> new Exchange();
        };

    }
}
