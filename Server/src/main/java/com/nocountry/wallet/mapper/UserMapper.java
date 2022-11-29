package com.nocountry.wallet.mapper;

import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.models.request.UserUpdateDTO;
import com.nocountry.wallet.models.response.UserDetailDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;
import com.nocountry.wallet.models.response.UserUpdateResponse;
import com.nocountry.wallet.security.config.service.AwsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component@AllArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    @Autowired
    AwsService awsService;


    public UserResponseDTO convert2DTO(UserEntity dto) {
        return mapper.map(dto, UserResponseDTO.class);
    }

    public UserEntity convert2Entity(UserCreateDTO dto) {
        //return mapper.map(dto, UserEntity.class);
        UserEntity user = new UserEntity();

        user = mapper.map(dto, UserEntity.class);

        user.setPhoto(awsService.uploadFileFromBase64(dto.getPhoto()));

        return user;
    }

    public UserDetailDTO convert2DetailDTO(UserEntity entity) {
        return mapper.map(entity, UserDetailDTO.class);
    }

    public List<UserDetailDTO> convertEntities2ListDTO(List<UserEntity> entities) {
        List<UserDetailDTO> result = new ArrayList<>();
        entities.forEach(entity -> result.add(convert2DetailDTO(entity)));
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
    /*
    public UserCreateDTO userEntity2DTO(UserEntity user){
        UserCreateDTO userDTO = new UserCreateDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
*/
}
