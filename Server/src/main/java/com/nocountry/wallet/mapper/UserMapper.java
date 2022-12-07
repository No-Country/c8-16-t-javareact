package com.nocountry.wallet.mapper;

import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.AccountDTO;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.request.UserUpdateDTO;
import com.nocountry.wallet.models.response.*;
import com.nocountry.wallet.service.AwsService;
import com.nocountry.wallet.utils.PaginationUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component @AllArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;
    private final AccountMapper accountMapper;

    private final AwsService awsService;


    public UserPaginatedResponse paginationUtils2UserPaginationResponse(PaginationUtils pagination) {

        Page page = pagination.getPage();
        List<UserEntity> userEntityList =  page.getContent();
        List<UserResponseDTO> userResponseList = userEntityList2UserResponseList(userEntityList);

        UserPaginatedResponse userPaginatedResponse = new UserPaginatedResponse();
        userPaginatedResponse.setUserPageContent(userResponseList);
        userPaginatedResponse.setPreviousPage(pagination.getPrevious());
        userPaginatedResponse.setNextPage(pagination.getNext());
        return userPaginatedResponse;
    }

    public List<UserResponseDTO> userEntityList2UserResponseList(List<UserEntity> userEntityList) {
        List<UserResponseDTO> mapperResponse = new ArrayList<>();
        for (UserEntity ent: userEntityList) {
            UserResponseDTO res = convert2DTO(ent);
            mapperResponse.add(res);
        }
        return mapperResponse;
    }

    public UserResponseDTO convert2DTO(UserEntity dto) {
        return mapper.map(dto, UserResponseDTO.class);
    }
    public UserRegisterDTO convert2RegDTO(UserEntity dto) {
        return mapper.map(dto, UserRegisterDTO.class);
    }

    public UserEntity convert2Entity(UserCreateDTO dto) {
        //return mapper.map(dto, UserEntity.class);
        UserEntity user = new UserEntity();

        user = mapper.map(dto, UserEntity.class);

        user.setPhoto(awsService.uploadFileFromBase64(dto.getPhoto()));

        return user;
    }

    public UserDetailDTO convert2DetailDTO(UserEntity entity, boolean loadAccounts) {
        UserDetailDTO dto = mapper.map(entity, UserDetailDTO.class);

        if (loadAccounts){
            List<AccountWithoutUserDTO> accDTOs = accountMapper.convert2ListWithoutUserDTO(entity.getAccounts());
            dto.setAccounts(accDTOs);
        }
        return dto;
    }

    public List<UserDetailDTO> convertEntities2ListDTO(List<UserEntity> entities) {
        List<UserDetailDTO> result = new ArrayList<>();
        entities.forEach(entity -> result.add(convert2DetailDTO(entity, false)));
        return result;
    }

    public UserEntity userUpdateDTO2Entity(UserUpdateDTO userUpdateDTO) {
        return mapper.map(userUpdateDTO, UserEntity.class);
    }

    public UserUpdateResponse userEntity2UserUpdateResponse(UserEntity userEntity) {
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        userUpdateResponse.setFirstName(userEntity.getFirstName());
        userUpdateResponse.setLastName(userEntity.getLastName());
        userUpdateResponse.setDni(userEntity.getDni());
        userUpdateResponse.setPassword(userEntity.getPassword());
        userUpdateResponse.setPhoto(userEntity.getPhoto());
        return userUpdateResponse;
    }

}
