package com.nocountry.wallet.service.impl;

import com.nocountry.wallet.models.request.AccountDTO;
import com.nocountry.wallet.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public AccountDTO createAccountWithToken(String token, String currency) {
        //Retrieve user on token:
        Authentication authentication = jwtTokenProvider.getAuthentication(token.substring(7));
        User authUser = userRepository.findByEmail(authentication.getName());

        // Encuentro la lista de cuentas con el token pasado por parametro
        List<Account> accountsByUserId = this.accountRepository.findAccountsByUserID(authUser.getId());

        // Busco si alguna de las cuentas pertenecientes al UserId ya tiene la currency igual a la pasada por parametro
        boolean repeatedAccount = accountsByUserId.stream()
                .anyMatch(i -> currency.equals(i.getCurrency().name()));

        if (!repeatedAccount) {
            Account accountEntity = new Account();
            accountEntity.setCurrency(CurrencyList.valueOf(currency));
            accountEntity.setCreationDate(Instant.now());
            accountEntity.setBalance(0.0);
            if (currency.equals("USD"))
                accountEntity.setTransactionLimit(1000.0);
            else
                accountEntity.setTransactionLimit(300000.0);

            accountEntity.setUpdateDate(Instant.now());
            accountEntity.setUser(userRepository.findById(authUser.getId()).get());
            accountRepository.save(accountEntity);

            return accountMapper.convertToAccountDTO(accountEntity);

        } else {
            throw new TransactionException(ErrorList.ACCOUNT_UNIQUE.getMessage());
        }

    }
}
