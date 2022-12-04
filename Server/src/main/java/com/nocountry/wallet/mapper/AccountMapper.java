package com.nocountry.wallet.mapper;

import com.nocountry.wallet.models.entity.AccountEntity;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.AccountDTO;
import com.nocountry.wallet.models.response.AccountDTOSlim;
import com.nocountry.wallet.models.response.AccountWithoutUserDTO;
import com.nocountry.wallet.models.response.UserDetailDTO;
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

    public AccountDTO convertToAccountDTO(AccountEntity account, boolean loadUsers) {
        AccountDTO dto = modelMapper.map(account, AccountDTO.class);
        dto.setUser(null);
        //        if (loadUsers){
//            UserDetailDTO userDTO = userMapper.convert2DetailDTO(account.getUser(), false);
//            dto.setUser(userDTO);
//        }
        return dto;
    }
    public AccountWithoutUserDTO convert2AccountOutUserDTO(AccountEntity account) {

        return modelMapper.map(account, AccountWithoutUserDTO.class);
    }
    public List<AccountWithoutUserDTO> convert2ListWithoutUserDTO(List<AccountEntity> entities) {
        List<AccountWithoutUserDTO> result = new ArrayList<>();
        entities.forEach(entity -> result.add(convert2AccountOutUserDTO(entity)));
        return result;
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

    public List<AccountDTO> accountEntityList2DTOList(List<AccountEntity> accountList, boolean loadUsers){
        List<AccountDTO> dtos = new ArrayList<>();
        for(AccountEntity account: accountList){
            dtos.add(convertToAccountDTO(account, loadUsers));
        }
        return dtos;
    }
}