package com.nocountry.wallet.mapper;

import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.models.request.AccountDTO;
import com.nocountry.wallet.models.response.AccountDTOSlim;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//
@Component
public class AccountMapper {
    @Autowired
    ModelMapper modelMapper;

    public AccountDTO convertToAccountDTO(AccountEntity account) {
        return modelMapper.map(account, AccountDTO.class);
    }

    public AccountEntity convertToAccount(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, AccountEntity.class);
    }

    public AccountDTOSlim convertToAccountDTOSlim(AccountEntity account){
        return modelMapper.map(account, AccountDTOSlim.class);
    }

    public List<AccountDTOSlim> convertToListDTOSlim(List<AccountEntity> accounts){
        return accounts.stream().map(this::convertToAccountDTOSlim).collect(Collectors.toList());
    }

    public List<AccountDTO> accountEntityList2DTOList(List<AccountEntity> accountList){
        List<AccountDTO> dtos = new ArrayList<>();
        for(AccountEntity account: accountList){
            dtos.add(convertToAccountDTO(account));
        }
        return dtos;
    }
}