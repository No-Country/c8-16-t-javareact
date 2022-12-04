package com.nocountry.wallet.service.impl;


import com.nocountry.wallet.auth.JwtUtils;
import com.nocountry.wallet.exception.NotFoundException;
import com.nocountry.wallet.exception.TransactionException;
import com.nocountry.wallet.mapper.AccountMapper;
import com.nocountry.wallet.mapper.TransactionMapper;
import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.AccountDTO;
import com.nocountry.wallet.models.response.AccountDTOSlim;
import com.nocountry.wallet.repository.AccountRepository;
import com.nocountry.wallet.repository.TransactionRepository;
import com.nocountry.wallet.repository.UserRepository;
import com.nocountry.wallet.service.IAccountService;
import com.nocountry.wallet.utils.GetTokenData;
import com.nocountry.wallet.utils.enumeration.CurrencyEnum;
import com.nocountry.wallet.utils.enumeration.ErrorEnum;
import com.nocountry.wallet.utils.enumeration.ErrorList;
import org.hibernate.type.CurrencyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private TransactionMapper transacctionMapper;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    GetTokenData getTokenData;

    @Override
    public AccountDTO createAccount(int userId, String currency) {
        // Encuentro la lista de cuentas con el UserId pasado por parametro
        List<AccountEntity> accountsByUserId = this.accountRepository.findAccountsByUserId((long) userId);
        // List<Account> accountsByUserId = this.accountRepository.findByUserID(userId);
        // TODO:Probar metodo para remplazar el de arriba
        // Busco si alguna de las cuentas pertenecientes al UserId ya tiene la currency
        // igual a la pasada por parametro
        boolean repeatedAccount = accountsByUserId.stream()
                .anyMatch(i -> currency.equals(i.getCurrency().name()));

        if (!repeatedAccount) {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setCurrency(CurrencyEnum.valueOf(currency));
            accountEntity.setCreationDate(Instant.now());
            accountEntity.setBalance(0.0);
            if (!currency.equals("ARS"))
                accountEntity.setTransactionLimit(1000.0);
            else
                accountEntity.setTransactionLimit(300000.0);

            accountEntity.setUpdateDate(Instant.now());
            accountEntity.setUserId((long) userId);
            accountRepository.save(accountEntity);

            return accountMapper.convertToAccountDTO(accountEntity, false);

        } else {
            return  null;
        }
    }

    @Override
    public List<AccountDTO> getAccountsByUser(Integer id) {
        if(userRepository.findById(Long.valueOf(id)).isEmpty())
            throw new NotFoundException(ErrorList.OBJECT_NOT_FOUND.getMessage());

        List<AccountEntity> result = this.accountRepository.findAccountsByUserId(Long.valueOf(id));

        return accountMapper.accountEntityList2DTOList(result, false);
    }


    @Override
    public List<AccountDTOSlim> getAccount(Integer user_id) {
        List<AccountEntity> accountsByUserId = this.accountRepository.findAccountsByUserId(Long.valueOf(user_id));
        List<AccountDTOSlim> listAccounts = accountMapper.convertToListDTOSlim(accountsByUserId);
        return listAccounts;
    }


    @Override
    public Map<String, Object> getAccounts() {
        List<AccountEntity> accounts = accountRepository.findAll();
        Map accountsMap = new HashMap<String, List<AccountEntity>>() {
            {
                put("Accounts", accounts);
            }
        };
        return accountsMap;
    }

    @Override
    public AccountEntity findByUserByCurrency(Long id, CurrencyEnum currency) {

        Optional<AccountEntity> optAcc = accountRepository.findByUserByCurrency(id,currency);
        if(!optAcc.isPresent()){
            throw new NotFoundException(ErrorEnum.OBJECT_NOT_FOUND.getMessage());
        }

        return optAcc.get();
    }


    @Override
    public AccountDTO createAccountWithToken(String token, String currency) {
        //Retrieve user on token:
        String authentication = jwtUtils.getJwt(token.substring(7));
        Optional<UserEntity> authUser = userRepository.findByEmail(authentication);


        List<AccountEntity> accountsByUserId = this.accountRepository.findAccountsByUserId(authUser.orElseThrow().getId());

        // Busco si alguna de las cuentas pertenecientes al UserId ya tiene la currency igual a la pasada por parametro
        boolean repeatedAccount = accountsByUserId.stream()
                .anyMatch(i -> currency.equals(i.getCurrency().name()));

        if (!repeatedAccount) {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setCurrency(CurrencyEnum.valueOf(currency));
            accountEntity.setCreationDate(Instant.now());
            accountEntity.setBalance(0.0);
            if (currency.equals("USD"))
                accountEntity.setTransactionLimit(1000.0);
            else
                accountEntity.setTransactionLimit(300000.0);

            accountEntity.setUpdateDate(Instant.now());
            accountEntity.setUser(userRepository.findById(authUser.orElseThrow().getId()).get());
            accountRepository.save(accountEntity);

            return accountMapper.convertToAccountDTO(accountEntity, false);

        } else {
            throw new TransactionException(ErrorList.ACCOUNT_UNIQUE.getMessage());
        }

    }

}
