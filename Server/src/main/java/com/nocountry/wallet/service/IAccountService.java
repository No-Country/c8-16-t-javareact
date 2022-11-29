package com.nocountry.wallet.service;

import com.nocountry.wallet.models.request.AccountDTO;

public interface IAccountService {

    AccountDTO createAccount(String token, String currency);
}
