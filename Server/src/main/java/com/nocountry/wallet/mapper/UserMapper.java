package com.nocountry.wallet.mapper;

import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component@AllArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public UserResponseDTO convert2DTO(UserEntity dto) {
        return mapper.map(dto, UserResponseDTO.class);
    }

    public UserEntity convert2Entity(UserCreateDTO dto) {
        return mapper.map(dto, UserEntity.class);
    }

}
