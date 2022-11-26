package com.nocountry.wallet.utils.transaction;

import com.nocountry.wallet.models.entity.AccountEntity;

public abstract class Transaction {

    //metodo para todas las transacciones
    public abstract void updateBalance(AccountEntity account, Double amount);
}
