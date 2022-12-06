package com.nocountry.wallet.service;

import com.nocountry.wallet.auth.AuthRequestDTO;
import com.nocountry.wallet.models.entity.UserEntity;
import com.nocountry.wallet.models.request.UserCreateDTO;
import com.nocountry.wallet.auth.AuthResponseDTO;
import com.nocountry.wallet.models.response.UserRegisterDTO;
import com.nocountry.wallet.models.response.UserResponseDTO;

public interface IAuthService {

    UserRegisterDTO saveUser(UserCreateDTO dto);
    AuthResponseDTO login(AuthRequestDTO authRequest);

    public boolean roleValidator(Long id, String token);

    public UserEntity getUserEntityByToken(String token);
}
