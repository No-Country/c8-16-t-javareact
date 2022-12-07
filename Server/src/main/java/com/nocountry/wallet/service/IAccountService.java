package com.nocountry.wallet.service;



import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.models.request.AccountDTO;
import com.nocountry.wallet.models.response.AccountDTOSlim;
import com.nocountry.wallet.utils.enumeration.CurrencyEnum;

import java.util.List;
import java.util.Map;

public interface IAccountService {
    public AccountDTO createAccount(int userId, String currency);
    AccountDTO createAccountWithToken(String token, String currency);
    List<AccountDTO> getAccountsByUser(Integer id);

    public List<AccountDTOSlim> getAccount(Integer user_id);

    // List<AccountDTO> getAccountsByUser(Integer id);

    Map<String, Object> getAccounts();

    //AccountPageDTO getAccountsByPage(Integer page);
    AccountEntity findByUserByCurrency(Long id, CurrencyEnum currency);
}